import java.util.stream.Stream;

public class TennisGame1 implements TennisGame {

    public static final int POINTS_TO_DEUCE = 3;
    public static final int POINTS_TO_WIN = 4;

    private final Player player1;
    private final Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        Stream.of(player1, player2)
                .filter(p -> p.name.equals(playerName))
                .forEach(Player::addPoint);
    }

    public String getScore() {
        if (isDeuce()) {
            return "Deuce";
        }
        if (isTie()) {
            return mapPointsToScore(player1.points) + "-All";
        }
        if (isWin()) {
            return "Win for " + Player.winning(player1, player2).name;
        }
        if (isAdvantage()) {
            return "Advantage " + Player.winning(player1, player2).name;
        }
        return mapPointsToScore(player1.points) + "-" + mapPointsToScore(player2.points);
    }

    private boolean isTie() {
        return player1.points == player2.points;
    }

    private boolean isDeuce() {
        return isTie() && player1.points >= POINTS_TO_DEUCE;
    }

    private boolean isWin() {
        return hasPointsEnoughToWin() && Math.abs(player1.points - player2.points) > 1;
    }

    private boolean isAdvantage() {
        return hasPointsEnoughToWin() && Math.abs(player1.points - player2.points) == 1;
    }

    private boolean hasPointsEnoughToWin() {
        return Math.max(player1.points, player2.points) >= POINTS_TO_WIN;
    }

    private String mapPointsToScore(int points) {
        String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};
        return scores[points];
    }

    private static class Player {
        final String name;
        int points = 0;

        Player(String name) {
            this.name = name;
        }

        void addPoint() {
            points += 1;
        }

        static Player winning(Player p1, Player p2) {
            return p1.points < p2.points ? p2 : p1;
        }
    }
}
