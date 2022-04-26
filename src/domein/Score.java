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

    public Score(int p10, int p11, int p12, int round, boolean doubleScore) {
        setBonusRound();
        this.p10 = p10;
        this.p11 = p11;
        this.p12 = p12;
        setDoubleScore(doubleScore);
        setBonus(round);
        setScore();
    }

    private void setDoubleScore(boolean doubleScore) {
        if(doubleScore) this.isDoubleScore = true;
        else this.isDoubleScore = false;
    }

    private void setScore() {
        score +=(1*p10)+(2*p11)+(4*p12);
        checkEarnedBonus();
        if(earnedBonus) score += this.bonus;
        if(isDoubleScore) score *=2;
    }

    private void checkEarnedBonus(){
        if(p10!=0 && p11!=0 && p12 !=0) earnedBonus = true;
        else earnedBonus = false;
    }

    private void setBonus(int round) {
        bonus = BonusList.get(round);
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
