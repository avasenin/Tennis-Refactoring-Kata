
public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name)) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
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
                tempScore = m_score1;
            } else {
                score += "-";
                tempScore = m_score2;
            }
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
        }
        return score;
    }


    private boolean isEndGame() {
        return m_score1 >= 4 || m_score2 >= 4;
    }

    private String getEndGameScore() {
        String score = "";
        int differenceInScore = m_score1 - m_score2;

        boolean hasSmallAdvantage = Math.abs(differenceInScore) == 1;
        if (hasSmallAdvantage) {
            score = "Advantage " + getWinningPlayer();
        } else {
            score = "Win for " + getWinningPlayer();
        }
        return score;
    }

    private String getWinningPlayer() {
        return m_score1 > m_score2 ? player1Name : player2Name;
    }

    private boolean isDraw() {
        return m_score1 == m_score2;
    }

    private String getDrawScore() {
        String score;
        switch (m_score1) {
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
