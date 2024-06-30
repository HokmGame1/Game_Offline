package src;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private  List<Card> hand = new ArrayList<>();
    private List<Card> currentSuitHand = new ArrayList<>();
    private  int Score=0;
    private boolean Hakem;
    private Card playedCard;

    public Player(String name) {
        this.name = name;
        this.Hakem=false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }

    public void setScore() {
        Score++;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getScore() {
        return Score;
    }

    public void setHakem(boolean hakem) {
        Hakem = hakem;
    }

    public boolean isHakem() {
        return Hakem;
    }

    public void setCurrentSuitHand() {
        currentSuitHand.clear();
        for (Card card : hand){
            if (card.getSuit()== CourtPiece4.getCurrentSuit()){
                currentSuitHand.add(card);
            }
        };
        if(currentSuitHand.size()==0){
            for (Card card : hand){
                currentSuitHand.add(card);
            };
        }
    }

    public List<Card> getCurrentSuitHand() {
        return currentSuitHand;
    }

    public void addCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public void playCard(int index) {
        if (index >= 0 && index < currentSuitHand.size()) {
            playedCard = currentSuitHand.get(index);

            System.out.println(name + " played " + playedCard);
        } else {
            System.out.println("Invalid card index.");
        }
    }
    public void removeCard(){
        hand.remove(playedCard);
    }

}
