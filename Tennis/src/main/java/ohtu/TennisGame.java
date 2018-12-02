package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    private String getSamePointsScore(int score) {
        switch (score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    private String getOverFourPointsScore(int score1, int score2) {
        int minusResult = score1 - score2;
        if (minusResult == 1) {
            return "Advantage player1";
        } else if (minusResult == -1) {
            return "Advantage player2";
        } else if (minusResult >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String getScoreWord(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        
        return "WrongValue";
    }

    public String getScore() {
        // Handle return value if the scores are the same
        if (m_score1 == m_score2) {

            return getSamePointsScore(m_score1);

        // Handle return value if either score is over 4
        } else if (m_score1 >= 4 || m_score2 >= 4) {

            return getOverFourPointsScore(m_score1, m_score2);
            
        // Handle return value if scores are not the same, or if neither is over 4
        } else {
            
            return getScoreWord(m_score1) + "-" + getScoreWord(m_score2);
        }
    }
}