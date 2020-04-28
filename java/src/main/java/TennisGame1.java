
public class TennisGame1 implements TennisGame {

    public static final int ADVANTAGE_SCORING_THRESHOLD = 4;
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
            score = getDrawScore(player1Points);
        } else if (isAdvantageScoring()) {
            score = getAdvantageScore();
        } else {
            score = getMidGameScore();
        }
        return score;
    }

    private String getMidGameScore() {
        return mapPointsToScore(player1Points) + "-" + mapPointsToScore(player2Points);
    }


    private boolean isAdvantageScoring() {
        return Math.max(player1Points, player2Points) >= ADVANTAGE_SCORING_THRESHOLD;
    }

    private String getAdvantageScore() {
        int differenceInScore = Math.abs(player1Points - player2Points);
        boolean hasSmallAdvantage = differenceInScore == 1;
        if (hasSmallAdvantage) {
            return "Advantage " + getWinningPlayer();
        }
        return "Win for " + getWinningPlayer();
    }

    private String getWinningPlayer() {
        return player1Points > player2Points ? player1Name : player2Name;
    }

    private boolean isDraw() {
        return player1Points == player2Points;
    }

    private String getDrawScore(int points) {
        if (points > 2) {
            return "Deuce";
        }
        return mapPointsToScore(points) + "-All";
    }

    private String mapPointsToScore(int points) {
        String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};
        return scores[points];
    }
}
