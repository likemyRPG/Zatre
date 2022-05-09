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

    // Constructor for the score class
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

    // Method to set the double score
    public void setDoubleScore(boolean doubleScore) {
        if(doubleScore) this.isDoubleScore = true;
        else this.isDoubleScore = false;
    }

    // Method to calculate the score
    public void setScore() {
        score=0;
        score +=(1*amountP10())+(2*amountP11())+(4*amountP12());
        checkEarnedBonus();
        if(earnedBonus) score += this.bonus;
        if(isDoubleScore) score *=2;
    }

    // Method to check if the player earned a bonus
    private void checkEarnedBonus(){
        if(p10!=0 && p11!=0 && p12 !=0) earnedBonus = true;
        else earnedBonus = false;
    }

    // Method to set the bonus for the row (get the value from the list BonusList)
    private void setBonus(int row) {
        bonus = BonusList.get(row);
    }

    // Method to return if the score is double
    public boolean isDoubleScore() {
        return isDoubleScore;
    }

    // Method to return the amount of 10's
    public int amountP10() {
        return p10;
    }

    // Method to return the amount of 11's
    public int amountP11() {
        return p11;
    }

    // Method to return the amount of 12's
    public int amountP12() {
        return p12;
    }

    // Method to set the amount of 10's
    public void setP10(int p10) {
        this.p10 = p10;
    }

    // Method to set the amount of 11's
    public void setP11(int p11) {
        this.p11 = p11;
    }

    // Method to set the amount of 12's
    public void setP12(int p12) {
        this.p12 = p12;
    }

    // Method to return the bonus
    public int getBonus(){ return bonus; }

    // Method to return the score
    public Integer getScore(){
        return score;
    }

    // Method to set the bonus list
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
