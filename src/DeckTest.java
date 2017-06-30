import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.AssertJUnit.*;


/**
 * Created by dcind on 6/29/2017.
 */
public class DeckTest {

    private static Long TESTSEED = 9001L;

    @Test
    public void testDeck(){
        Deck deck = new Deck();

        //Deck was properly instantiated
        assertNotNull(deck);

        //Deck should have 52 unique cards
        assertEquals(52, deck.getCards().size());

        //All 52 possible cards are present in the deck. You can't add duplicates
        assertFalse(deck.insertCard(new Card(Card.Ranks.Eight, Card.Suits.Clubs)));

        //Deck should still have just 52 cards after attempting to add a duplicate
        assertEquals(52, deck.getCards().size());
    }

    @Test
    public void testShuffle(){
        Deck deck = new Deck();

        Set<Card> startingDeck = deck.getCards();

        //Use a known seed so we can verify cards order was changed up as expected
        deck.shuffle(TESTSEED);

        //We're using a known seed, so we know the top card is always 7 of Clubs, which is different
        //than the card of the sorted deck we started with
        Card sevenClubs = new Card(Card.Ranks.Seven, Card.Suits.Clubs);
        assertFalse(deck.getCards().iterator().next().equals(startingDeck.iterator().next()));

        Card topCard = deck.dealOneCard();
        assertEquals(sevenClubs, topCard);
    }

    @Test
    public void testDeal(){
        Deck deck = new Deck();

        deck.shuffle();

        //Draw 52 times and verify a card is returned
        for(int i=0; i<52; i++){
            Card topCard = deck.dealOneCard();
            assertNotNull(topCard);
        }

        //Deck should be empty, and a null returned when attempting to draw
        assertNull(deck.dealOneCard());
    }

}