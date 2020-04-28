
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
        String score = "";
        int tempScore;
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = player1Points;
            } else {
                score += "-";
                tempScore = player2Points;
            }
            score += mapPointsToScore(tempScore);
        }
        return score;
    }

    private String mapPointsToScore(int tempScore) {
        String score = "";
        switch (tempScore) {
            case 0:
                score += "Love";
                break;
            case 1:
                score += "Fifteen";
                break;
            case 2:
                score += "Thirty";
                break;
            case 3:
                score += "Forty";
                break;
        }
        return score;
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
        String score;
        switch (player1Points) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            default:
                score = "Deuce";
                break;

        }
        return score;
    }
}
