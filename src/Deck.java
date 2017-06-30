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
        Shuffle without supplying a seed
        Calls shuffle(seed) using current time as the seed
     */
    public void shuffle(){
        shuffle(new Date().getTime());
    }


    /*
        Randomize the order of cards in the deck, using a supplied seed
     */
    public void shuffle(Long seed){
        //Can't shuffle a deck with no cards
        if(cards != null && cards.size() > 0){

            int deckSize = cards.size();

            //Random int based on the seed, from 0-size of the deck at the time of shuffling
            Random randGen = new Random(seed);

            //To add elements to as we "shuffle" the deck of cards
            Card[] shuffledCards = new Card[deckSize];

            //Iterate over the deck, and attempt to slot each into a randomized position in the new shuffled array
            for(Card curCard : cards){
                int randSlot = randGen.nextInt(deckSize);

                //The slot could have already been taken, so we circle through the list
                //until the next open slot is found
                while(shuffledCards[randSlot] != null){
                    randSlot = (randSlot+1)%deckSize;
                }
                shuffledCards[randSlot] = curCard;
            }


            cards = new LinkedHashSet<Card>(Arrays.asList(shuffledCards));
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
        Clears the deck, and reassembles it, in order, with all 52 cards
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
