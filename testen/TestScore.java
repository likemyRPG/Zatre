package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Score;



public class TestScore {

    private Score s;

    private static final int GELDIG_P10 = 1;
    private static final int GELDIG_P11 = 2;
    private static final int GELDIG_P12 = 3;
    private static final int VALUE_P = 100;

    @BeforeEach
    public void BeforeEach() {

        s = new Score(GELDIG_P10,GELDIG_P11,GELDIG_P12,true);
    }
    //getters
    @Test
    public void maakScore_getP10_geeftP10() {
    	int ten = s.amountP10();
        Assertions.assertEquals(ten,GELDIG_P10);
    }

    @Test
    public void maakScore_getP11_geeftP11() {
    	int elev = s.amountP11();
        Assertions.assertEquals(elev,GELDIG_P11);
    }
    
    @Test
    public void maakScore_getP12_geeftP12() {

    	int twel = s.amountP12();
        Assertions.assertEquals(twel,GELDIG_P12);
    }

    public void maakScore_isDoubleScore_geeftBooleanDoubleScore() {
    	Assertions.assertTrue(s.isDoubleScore());
    }
    //setters
    @Test
    public void maakScore_setP10_verandertValueP10() {

    	s.setP10(VALUE_P);
    	int ten = s.amountP10();
        Assertions.assertEquals(ten,VALUE_P);
    }
    
    @Test
    public void maakScore_setP11_verandertValueP11() {

    	s.setP11(VALUE_P);
    	int elev = s.amountP11();
        Assertions.assertEquals(elev,VALUE_P);
    }

    @Test
    public void maakScore_setP12_verandertValueP12() {

    	s.setP12(VALUE_P);
    	int twel = s.amountP12();
        Assertions.assertEquals(twel,VALUE_P);
    }

    @Test
    public void maakScore_setDoubleScoreTrue_verandertValueIsDoubleScoreTrue() {
    	s.setDoubleScore(true);
    	boolean ds = s.isDoubleScore();
    	Assertions.assertTrue(ds);
    }

    @Test
    public void maakScore_setScore_calculatesScore() {
    	s.setScore();
    	Assertions.assertEquals(40,s.getScore());
    	
    	
    }


}
