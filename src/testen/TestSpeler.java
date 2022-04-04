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
    private static final int AANTAL_KANSEN = 5;
    language ln = new language();
    // static final int GELDIGE_KANSEN = 5;
    private Speler speler;
    @BeforeEach
    public void BeforeEach() {
        ln.setGekozenTaal("nl");
        ln.taal();
        
        speler = new Speler(GELDIGE_NAAM,GELDIGE_GEBOORTE, AANTAL_KANSEN);
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Speler(n,GELDIGE_GEBOORTE, AANTAL_KANSEN);});
    }

    @ParameterizedTest
    @ValueSource(ints = {2020, 2021, 2017,0,-500,-1000,3000})
    public void maakSpeler_geldigeNaam_ongeldigeGeboorte_Exception(int d) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Speler(GELDIGE_NAAM,d, AANTAL_KANSEN);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "User", "AbCd","a","ab","abc"})
    public void maakSpeler_naamTeKort_Exception(String n) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Speler(n,GELDIGE_GEBOORTE, AANTAL_KANSEN);});
    }

}
