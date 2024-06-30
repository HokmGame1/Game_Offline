package src;

public class Team{
    private String TeamName;
    private  Player player1;
    private Player player2;
    private int Score=12;
    private int Round=03;
    public Team(String teamName, Player player1, Player player2){
        this.TeamName=teamName;
        this.player1=player1;
        this.player2=player2;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }
    public String getTeamName() {
        return TeamName;
    }
    public int getRound() {
        return Round;
    }
    public void setRound(int round) {
        Round=Round+round;
    }
    public int getScore() {
        return Score;
    }
    public void setScore() {
        Score = getPlayer1().getScore() + getPlayer2().getScore();
    }

    public void setScore(int score) {
        Score = score;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
