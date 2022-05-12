package testen;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Score;
import domein.Scoreblad;

public class TestScoreblad {

	
    private Scoreblad sb;
    private Score s1,s2; 

    private static final int GELDIG_P10 = 1;
    private static final int GELDIG_P11 = 2;
    private static final int GELDIG_P12 = 3;
    private static final int VALUE_P = 100;

    @BeforeEach
    public void BeforeEach() {


        sb = new Scoreblad();
        s1 = new Score(GELDIG_P10,GELDIG_P11,GELDIG_P12,true);
        s2 = new Score(GELDIG_P10,GELDIG_P11,GELDIG_P12,true);
        sb.addScore(s1);
		sb.addScore(s2);
		 
    }
	
    @Test
	public void maakScoreblad_addScore_scoreToevoegen() {

		 List<Score> scor = new ArrayList<>();
		 scor.add(s1);
		 scor.add(s2);
		 Assertions.assertEquals(scor,sb.getScores());
	 }
    
    @Test
	public void maakScoreblad_setScore_getTotalScore_geeftTotaleScoreVanAlleScores() {
    	sb.setScore();
    	int tot = sb.getTotalScore();
    	
    	Assertions.assertEquals(tot,80);
	 }





}
