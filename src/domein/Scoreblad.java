package domein;

import java.util.ArrayList;
import java.util.List;

public class Scoreblad {
    // Variables

    private List<Score> scores;

    /**
     * Constructor for the Scoreblad class.
     */
    // Constructor
    public Scoreblad(){
        scores = new ArrayList<>();
    }

    /**
     * @param score the score to be added.
     *              Method to add a score to the list.
     */
    public void addScore(Score score){
        scores.add(score);
    }

    /**
     * Method to calculate the total score of all the rows
     */
    public void setScore(){
        scores.forEach(score -> score.setScore());
    }

    /**
     * @return the list of scores.
     */
    // Method to get the list of scores
    public List<Score> getScores(){
        return scores;
    }

    /**
     * @return the total score.
     *             Method to get the total score.
     */
    public int getTotalScore(){
        int totalScore = 0;
        for(Score score : scores){
            totalScore += score.getScore();
        }
        return totalScore;
    }

}
