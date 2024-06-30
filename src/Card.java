package src;

public class Card {
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    private Suit Hokm;
    private Suit CurrentSuit;

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
    private int ranking;
    public int getRanking(){
        switch (getRank()){
            case ACE :
                ranking= 13;
                break;
            case KING:
                ranking= 12;
                break;
            case QUEEN:
                ranking= 11;
                break;
            case JACK:
                ranking= 10;
                break;
            case TEN:
                ranking= 9;
                break;
            case NINE:
                ranking= 8;
                break;
            case EIGHT:
                ranking= 7;
                break;
            case SEVEN:
                ranking= 6;
                break;
            case SIX:
                ranking= 5;
                break;
            case FIVE:
                ranking= 4;
                break;
            case FOUR:
                ranking= 3;
                break;
            case THREE:
                ranking= 2;
                break;
            case TWO:
                ranking= 1;
                break;
        }
        return ranking;
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
