
public class TennisGame1 implements TennisGame {

    private int player1Points = 0;
    private int player2Points = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name)) {
            player1Points += 1;
        } else {
            player2Points += 1;
        }
    }

    public String getScore() {
        String score = "";
        if (isDraw()) {
            score = getDrawScore();
        } else if (isEndGame()) {
            score = getEndGameScore();
        } else {
            score = getMidGameScore();
        }
        return score;
    }

    private String getMidGameScore() {
        return mapPointsToScore(player1Points) + "-" + mapPointsToScore(player2Points);
    }


    private boolean isEndGame() {
        return player1Points >= 4 || player2Points >= 4;
    }

    private String getEndGameScore() {
        String score;
        int differenceInScore = Math.abs(player1Points - player2Points);
        boolean hasSmallAdvantage = differenceInScore == 1;

        if (hasSmallAdvantage) {
            score = "Advantage " + getWinningPlayer();
        } else {
            score = "Win for " + getWinningPlayer();
        }

        return score;
    }

    private String getWinningPlayer() {
        return player1Points > player2Points ? player1Name : player2Name;
    }

    private boolean isDraw() {
        return player1Points == player2Points;
    }

    private String getDrawScore() {
        var points = player1Points;
        if (points > 2) {
            return "Deuce";
        }
        return mapPointsToScore(points) + "-All";
    }

    private String mapPointsToScore(int tempScore) {
        String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};
        return scores[tempScore];
    }

}
