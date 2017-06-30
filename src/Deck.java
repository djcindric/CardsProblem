import java.util.*;

/**
 * Created by dcind on 6/29/2017.
 */
public class Deck{
    private LinkedHashSet<Card> cards;

    /*
        Create a clean (sorted) deck by default
     */
    public Deck(){
        resetDeck();
    }

    public LinkedHashSet<Card> getCards() {
        return cards;
    }

    public void setCards(LinkedHashSet<Card> cards) {
        this.cards = cards;
    }

    /*
        Inserts a card on the top of the deck. Set structure prevents duplicates for us
     */
    public boolean insertCard(Card card){
        return cards.add(card);
    }

    /*
        Call shuffle with current time as the seed
     */
    public void shuffle(){
        shuffle(new Date().getTime());
    }


    /*
        Randomize the order of cards in the deck
     */
    public void shuffle(Long seed){
        //Can't shuffle a deck with no cards
        if(cards != null && cards.size() > 0){

            int deckSize = cards.size();

            //Random int based on the seed, from 0-size of the deck at the time of shuffling
            Random ranGen = new Random(seed);

            List<Card> shuffledList = new ArrayList<Card>(Collections.nCopies(deckSize, null));

            //Iterate over the current cards, and attempt to slot it into a randomized position
            //in the new shuffledList
            Iterator<Card> itr = cards.iterator();
            while(itr.hasNext()){
                Card curCard = itr.next();
                int ranSlot = ranGen.nextInt(deckSize);
                shuffledList.add(ranSlot, curCard);
            }

            //Due to collisions, the list could have multiple nulls remaining as List.Add keeps moving right until it
            //finds an empty slot for the card. Remove those and we're back down to the same size deck we started with
            shuffledList.removeAll(Collections.singleton(null));

            cards = new LinkedHashSet<Card>(shuffledList);
        }
    }

    /*
        Removes and returns the card from the "top" of the deck
        Returns null when deck is empty
     */

    public Card dealOneCard(){
        if(cards != null && cards.size() > 0){
            Card topCard = cards.iterator().next();
            cards.remove(topCard);
            return topCard;
        }else{
            return null;
        }
    }

    /*
        Clears the deck, and reassembles it in order with all 52 cards
     */
    public void resetDeck(){
        cards = new LinkedHashSet<>();

        for(Card.Suits suit : Card.Suits.values()){
            for(Card.Ranks rank : Card.Ranks.values()){
                insertCard(new Card(rank, suit));
            }
        }

    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }


}
