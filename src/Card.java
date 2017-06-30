/**
 * Created by dcind on 6/29/2017.
 */
public class Card implements Comparable<Card>{

    /*
        enums to represent the suits (ordered alphabetically) and ranks (Ordered with Aces low) of a standard 52 card deck
     */
    public enum Suits {
        Clubs, Diamonds, Hearts, Spades
    }

    public enum Ranks {
        Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
    }

    public Card(Ranks rank, Suits suit) {
        this.rank = rank;
        this.suit = suit;
    }

    private Ranks rank;
    private Suits suit;

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    /*
        Compare cards based on their rank and suit (Enums are already ordered)
     */
    @Override
    public int compareTo(Card card) {
        if(this.rank.equals(card.rank)){
            return this.suit.compareTo(card.suit);
        }else {
            return this.rank.compareTo(card.rank);
        }
    }

    /*
        Cards are equal if they have the same suit and rank
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (suit != null ? suit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit=" + suit +
                '}';
    }

}
