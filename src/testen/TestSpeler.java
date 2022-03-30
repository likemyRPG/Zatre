package testen;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import domein.Speler;
import persistence.language;

class TestSpeler {

    private static final String GELDIGE_NAAM = "testGebruiker";
    private static final int GELDIGE_GEBOORTE = 2003;
    // static final int GELDIGE_KANSEN = 5;
    private Speler speler;
    @BeforeEach
    public void BeforeEach() {
    	
    	language ln = new language();
        ResourceBundle rb = ln.taal2("nl");
        
        rb = ln.taal2("nl");
        
        speler = new Speler(GELDIGE_NAAM,GELDIGE_GEBOORTE);
    }

    @Test
    public void maakSpeler_geldigeNaam_geldigeGeboorte_maaktSpeler() {
        Assertions.assertEquals(GELDIGE_NAAM,speler.getGebruikersnaam());
        Assertions.assertEquals(GELDIGE_GEBOORTE,speler.getGeboortejaar());
    }


    @ParameterizedTest
    @NullAndEmptySource
	@ValueSource(strings = {"a"," ","  ","\t","\t\t"})
    public void maakSpeler_ongeldigeNaam_geldigeGeboorte_Exception(String n) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Speler(n,GELDIGE_GEBOORTE);});
    }

    @ParameterizedTest
    @ValueSource(ints = {2020, 2021, 2017,0,-500,-1000,3000})
    public void maakSpeler_geldigeNaam_ongeldigeGeboorte_Exception(int d) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Speler(GELDIGE_NAAM,d);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "User", "AbCd","a","ab","abc"})
    public void maakSpeler_naamTeKort_Exception(String n) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Speler(n,GELDIGE_GEBOORTE);});
    }

}
