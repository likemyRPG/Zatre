package testen;

import domein.Spel;
import domein.Speler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import persistence.language;

import java.util.ArrayList;
import java.util.List;

public class TestSpel {

    private static final Speler p1 = new Speler("azert",2003,5);
    private static final Speler p2 = new Speler("aeef",2003,5);

    language ln = new language();
    // static final int GELDIGE_KANSEN = 5;
    private Spel spel;
    @BeforeEach
    public void BeforeEach() {
        ln.setGekozenTaal("nl");
        ln.taal();
        List<Speler> spelers = new ArrayList<>();
        spelers.add(p1);
        spelers.add(p2);
        spel = new Spel(spelers);
    }
/*
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

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -3, -4, -5})
    public void maakSpeler_aantalKansen_Exception(int a) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Speler(GELDIGE_NAAM,GELDIGE_GEBOORTE, a);});
    }
    */

}
