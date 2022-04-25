package domein;

import java.util.ArrayList;
import java.util.List;

public class Scoreblad {
    private List<Score> scores;

    public Scoreblad(){
        scores = new ArrayList<>();
    }

    public void addScore(Score score){
        scores.add(score);
    }

    public List<Score> getScores(){
        return scores;
    }

    public int getTotalScore(){
        int totalScore = 0;
        for(Score score : scores){
            totalScore += score.getScore();
        }
        return totalScore;
    }

}
