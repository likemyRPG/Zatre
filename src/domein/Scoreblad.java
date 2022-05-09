package domein;

import java.util.ArrayList;
import java.util.List;

public class Scoreblad {
    // Variables
    private List<Score> scores;

    // Constructor
    public Scoreblad(){
        scores = new ArrayList<>();
    }

    // Method to add a score to the list
    public void addScore(Score score){
        scores.add(score);
    }

    // Method to calculate the total score of all the rows
    public void setScore(){
        scores.forEach(score -> score.setScore());
    }

    // Method to get the list of scores
    public List<Score> getScores(){
        return scores;
    }

    // Method to get the total score
    public int getTotalScore(){
        int totalScore = 0;
        for(Score score : scores){
            totalScore += score.getScore();
        }
        return totalScore;
    }

}
