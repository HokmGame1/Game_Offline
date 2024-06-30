package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class CourtPiece4 {
    private static final int HAND_SIZE = 13;
    private Deck deck;
    private final HashMap<Integer, Player> players = new HashMap<Integer, Player>();;
    private final Team Team_A;
    private final Team Team_B;
    private static Card.Suit CurrentSuit=null;
    private Card.Suit Hokm;
    private Player Hakem, higherPlayer;
    private int indexOfHakem,indexOfHigherPlayer;

    public void setHakem() {
        if(Team_A.getRound()==0 && Team_B.getRound()==0){
            this.indexOfHakem=new Random().nextInt(4);
            this.Hakem=players.get(indexOfHakem);
        }else {
            this.Hakem=players.get((indexOfHakem+1)%4);
        }
    }

    public Player getHakem() {
        return Hakem;
    }
    public void setHokm(){
        System.out.println("select Hokm:\n1.SPADES\n2.HEARTS\n3.CLUBS\n4.DIAMONDS");
        Scanner scanner = new Scanner(System.in);
//        int x=scanner.nextInt();
        switch (scanner.nextInt()){
            case 1:
                Hokm=Card.Suit.SPADES;
                break;
            case 2:
                Hokm=Card.Suit.HEARTS;
                break;
            case 3:
                Hokm=Card.Suit.CLUBS;
                break;
            case 4:
                Hokm=Card.Suit.DIAMONDS;
                break;
            default:
                System.out.println("invalid input");
        }
    }

    public int getPlayerIndex(Player player){
        int x1=0;
        for (Map.Entry<Integer, Player> entry: players.entrySet()) {
            if (entry.getValue().equals(player)) {
//                System.out.println(entry.getKey());
                x1=entry.getKey();
            }
        }
        return x1;
    }

    public Card.Suit getHokm() {
        return Hokm;
    }

    public CourtPiece4() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            players.put(i, new Player(scanner.next()));
        }
        this.Team_A = new Team("A", players.get(0), players.get(2));
        this.Team_B = new Team("B", players.get(1), players.get(3));
    }

    public void newHand(){
        deck=new Deck();
        for (int i : players.keySet()) {
            players.get(i).clearHand();
            players.get(i).addCards(deck.deal(HAND_SIZE));
//            System.out.println(players.get(i).getName() + "'s hand: " + players.get(i).getHand());
        }
    }
    public void showHand(){
        for (int i : players.keySet()) {
            System.out.println(players.get(i).getName() + "'s hand: " + players.get(i).getHand());
        }
    }
    public void showHakemHand(){
        System.out.println(getHakem().getName() + "'s hand: " + getHakem().getHand().subList(0,5));
    }
    public void playRound(){

        while (Team_B.getScore()<7 && Team_A.getScore()<7){

            if((Team_A.getScore()==0)&&(Team_B.getScore()==0)){
                setHigherPlayer(getHakem());
            }

            setCurrentSuit(null);
            int k=getPlayerIndex(getHigherPlayer());

            for (int j=k; j<k+4; j++) {
                players.get(j%4).setCurrentSuitHand();
                System.out.println("\n"+players.get(j%4).getCurrentSuitHand());

                System.out.print(players.get(j%4).getName()+", select a card to play (0-" + (players.get(j%4).getCurrentSuitHand().size() - 1) + "): ");
                Scanner scanner = new Scanner(System.in);
                int cardIndex = scanner.nextInt();
                players.get(j%4).playCard(cardIndex);

                if(j==k){
                    setHigherPlayer(players.get(j%4));
                    setCurrentSuit(players.get(j%4).getPlayedCard().getSuit());
                    System.out.println(getHigherPlayer().getName()+"\n"+getHigherPlayer().getPlayedCard());
                }else {
                    setHigherPlayer(getHigherPlayer(), players.get(j%4));
                    System.out.println(getHigherPlayer().getName()+"\n"+getHigherPlayer().getPlayedCard());
                }
                players.get(j%4).removeCard();
            }
            getHigherPlayer().setScore();
            Team_A.setScore();
            Team_B.setScore();
            System.out.println("\n Higher Player: "+getHigherPlayer().getName());
            System.out.println("Team "+Team_A.getTeamName()+" Score: "+Team_A.getScore());
            System.out.println("Team "+Team_B.getTeamName()+" Score: "+Team_B.getScore());

        }

//            team A win
        if (Team_A.getScore()==7 && Team_B.getScore()!=0){
            Team_A.setRound(1);
            System.out.println("Team "+Team_A.getTeamName()+" won this Round!");

//            team B win
        } else if (Team_A.getScore()!=0 && Team_B.getScore()==7) {
            Team_B.setRound(1);
            System.out.println("Team "+Team_B.getTeamName()+" won this Round!");

//            team A KOT
        } else if (Team_A.getScore()==7 && Team_B.getScore()==0) {
            if ((Team_A.getPlayer1()==getHakem())||(Team_A.getPlayer2()==getHakem())){
                Team_A.setRound(2);
            }else {
                Team_A.setRound(3);
            }
            System.out.println("Team "+Team_A.getTeamName()+" won this Round!!!");

//            team B KOT
        } else if (Team_B.getScore()==7 && Team_A.getScore()==0) {
            if ((Team_B.getPlayer1()==getHakem())||(Team_B.getPlayer2()==getHakem())){
                Team_B.setRound(2);
            }else {
                Team_B.setRound(3);
            }
            System.out.println("Team "+Team_B.getTeamName()+" won this Round!!!");
        }

        System.out.println("Team "+Team_A.getTeamName()+" : "+Team_A.getRound());
        System.out.println("Team "+Team_B.getTeamName()+" : "+Team_B.getRound());
    }

    public static void setCurrentSuit(Card.Suit currentSuit) {
        CurrentSuit = currentSuit;
    }

    public static Card.Suit getCurrentSuit() {
        return CurrentSuit;
    }


    public Player getHigherPlayer() {
        return higherPlayer;
    }

    public void setHigherPlayer(Player player){
        higherPlayer=player;
    }
    public void setHigherPlayer(Player player1, Player player2){
        if(player1.getPlayedCard().getSuit()==getHokm() && player2.getPlayedCard().getSuit()!=getHokm()){
            higherPlayer=player1;
        }
        if(player1.getPlayedCard().getSuit()!=getHokm() && player2.getPlayedCard().getSuit()==getHokm()){
            higherPlayer=player2;
        }
        if(player1.getPlayedCard().getSuit()==getHokm() && player2.getPlayedCard().getSuit()==getHokm()){
            if(player1.getPlayedCard().getRanking() > player2.getPlayedCard().getRanking()){
                higherPlayer=player1;
            } else {
                higherPlayer=player2;
            }
        }
        if(player1.getPlayedCard().getSuit()!=getHokm() && player2.getPlayedCard().getSuit()!=getHokm()){
            if(player1.getPlayedCard().getSuit()==getCurrentSuit() && player2.getPlayedCard().getSuit()!=getCurrentSuit()){
                higherPlayer=player1;
            }
            if(player1.getPlayedCard().getSuit()!=getCurrentSuit() && player2.getPlayedCard().getSuit()==getCurrentSuit()){
                higherPlayer=player2;
            }
            if(player1.getPlayedCard().getSuit()==getCurrentSuit() && player2.getPlayedCard().getSuit()==getCurrentSuit()){
                if(player1.getPlayedCard().getRanking() > player2.getPlayedCard().getRanking()){
                    higherPlayer=player1;
                } else {
                    higherPlayer=player2;
                }
            }
        }
    }

    public Team getTeam_A() {
        return Team_A;
    }

    public Team getTeam_B() {
        return Team_B;
    }
    public void resetScores(){
        getTeam_A().getPlayer1().setScore(0);
        getTeam_A().getPlayer2().setScore(0);
        getTeam_B().getPlayer1().setScore(0);
        getTeam_B().getPlayer2().setScore(0);

        getTeam_A().setScore();
        getTeam_B().setScore();
    }
    public void startGame() {
        while (Team_A.getRound()<7 && Team_B.getRound()<7){
            setHakem();
            System.out.println(getHakem().getName());
            newHand();
            showHakemHand();
            setHokm();
            showHand();
            resetScores();
            playRound();
        }
        if(Team_A.getRound()>=7){
            System.out.println("winner is Team: "+Team_A.getTeamName());
        }else {
            System.out.println("winner is Team: "+Team_B.getTeamName());
        }
    }
}
