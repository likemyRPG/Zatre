package domein;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Score {
    private boolean isDoubleScore;
    private int p10 = 0;
    private int p11 = 0;
    private int p12 = 0;
    private int bonus;
    private int score;
    private boolean earnedBonus;
    List<Integer> BonusList;
    int row = 0;

    public Score(int p10, int p11, int p12, boolean doubleScore) {
        setBonusRound();
        this.p10 = p10;
        this.p11 = p11;
        this.p12 = p12;
        setDoubleScore(doubleScore);
        row++;
        setBonus(row);
        setScore();
    }

    public void setDoubleScore(boolean doubleScore) {
        if(doubleScore) this.isDoubleScore = true;
        else this.isDoubleScore = false;
    }

    public void setScore() {
        score=0;
        score +=(1*amountP10())+(2*amountP11())+(4*amountP12());
        checkEarnedBonus();
        if(earnedBonus) score += this.bonus;
        if(isDoubleScore) score *=2;
    }

    private void checkEarnedBonus(){
        if(p10!=0 && p11!=0 && p12 !=0) earnedBonus = true;
        else earnedBonus = false;
    }

    private void setBonus(int row) {
        bonus = BonusList.get(row);
    }

    public boolean isDoubleScore() {
        return isDoubleScore;
    }

    public int amountP10() {
        return p10;
    }
    public int amountP11() {
        return p11;
    }
    public int amountP12() {
        return p12;
    }

    public void setP10(int p10) {
        this.p10 = p10;
    }
    public void setP11(int p11) {
        this.p11 = p11;
    }
    public void setP12(int p12) {
        this.p12 = p12;
    }

    public int getBonus(){ return bonus; }

    public Integer getScore(){
        return score;
    }

    private void setBonusRound(){
        BonusList = new ArrayList<>();
        int points = 3;
        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 4; j++){
                BonusList.add(points);
            }
            points++;
        }
        System.out.println(BonusList);
    }
}
