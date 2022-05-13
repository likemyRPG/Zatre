package domein;

import java.util.ArrayList;
import java.util.List;

public class Score {
    //region Variables
    private boolean isDoubleScore;
    private int p10 = 0;
    private int p11 = 0;
    private int p12 = 0;
    private int bonus;
    private int score;
    private boolean earnedBonus;
    List<Integer> BonusList;
    int row = 0;
    //endregion

    /**
     * @param p10 is the amount of times the player scored a 10 in a round.
     * @param p11 is the amount of times the player scored a 11 in a round.
     * @param p12 is the amount of times the player scored a 12 in a round.
     * @param doubleScore is a boolean to check if the player earned a double score.
     *                    Constructor for the Score class.
     */
    public Score(int p10, int p11, int p12, boolean doubleScore) {
        // Set the bonuses to an arraylist
        setBonusRound();
        // Amount of times there is a 10, 11, 12
        this.p10 = p10;
        this.p11 = p11;
        this.p12 = p12;
        // Set the double score
        setDoubleScore(doubleScore);
        row++;
        // Set the bonus for the row
        setBonus(row);
        // Set the score
        setScore();
    }

    /**
     * @param doubleScore is a boolean to check if the player earned a double score.
     *                    Method to set the double score.
     */
    // Method to set the double score
    public void setDoubleScore(boolean doubleScore) {
        if(doubleScore) this.isDoubleScore = true;
        else this.isDoubleScore = false;
    }

    /**
     * Method ot calculate the score.
     */
    public void setScore() {
        score=0;
        score +=(1*amountP10())+(2*amountP11())+(4*amountP12());
        checkEarnedBonus();
        if(earnedBonus) score += this.bonus;
        if(isDoubleScore) score *=2;
    }

    /**
     * Method to check if the player earned a bonus.
     */
    private void checkEarnedBonus(){
        if(p10!=0 && p11!=0 && p12 !=0) earnedBonus = true;
        else earnedBonus = false;
    }

    /**
     * @param row is the row of the Scoreboard.
     *           Method to set the bonus for the row.
     */
    // Method to set the bonus for the row (get the value from the list BonusList)
    private void setBonus(int row) {
        bonus = BonusList.get(row);
    }

    /**
     * @return true if the player earned a double score.
     */
    public boolean isDoubleScore() {
        return isDoubleScore;
    }

    /**
     * @return the amount of times the player scored a 10 in a round.
     */
    public int amountP10() {
        return p10;
    }

    /**
     * @return the amount of times the player scored a 11 in a round.
     */
    public int amountP11() {
        return p11;
    }

    /**
     * @return the amount of times the player scored a 12 in a round.
     */
    public int amountP12() {
        return p12;
    }

    /**
     * @param p10 is the amount of times the player scored a 10 in a round.
     *            Method to set the amount of times the player scored a 10 in a round.
     */
    public void setP10(int p10) {
        this.p10 = p10;
    }

    /**
     * @param p11 is the amount of times the player scored a 11 in a round.
     *            Method to set the amount of times the player scored a 11 in a round.
     */
    public void setP11(int p11) {
        this.p11 = p11;
    }

    /**
     * @param p12 is the amount of times the player scored a 12 in a round.
     *            Method to set the amount of times the player scored a 12 in a round.
     */
    public void setP12(int p12) {
        this.p12 = p12;
    }

    /**
     * @return the bonus for the row.
     *           Method to get the bonus for the row.
     */
    public int getBonus(){ return bonus; }

    /**
     * @return the score.
     *          Method to get the score.
     */
    public Integer getScore(){
        return score;
    }

    /**
     * Method to set the BonusList.
     */
    private void setBonusRound(){
        BonusList = new ArrayList<>();
        int points = 3;
        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 4; j++){
                BonusList.add(points);
            }
            points++;
        }
    }
}
