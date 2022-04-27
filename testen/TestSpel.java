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

    private static Speler p1;
    private static Speler p2;

    language ln = new language();
    // static final int GELDIGE_KANSEN = 5;
    private Spel spel;
    @BeforeEach
    public void BeforeEach() {
        ln.setGekozenTaal("nl");
        ln.taal();
        p1 = new Speler("azehrt",2003,5);
        p2 = new Speler("aeeygtf",2003,5);
        List<Speler> spelers = new ArrayList<>();
        spelers.add(p1);
        spelers.add(p2);
        spel = new Spel(spelers);
    }
    @Test
    public void setNextPlayer_selectsNextPlayer() {
        spel.setNextPlayer();
        Speler prevPlayer = spel.getPreviousPlayer();
        Speler currentPlayer = spel.getCurrentPlayer();
        Assertions.assertEquals(p1 ,prevPlayer);
        Assertions.assertEquals(p2,currentPlayer);
    }

    @Test
    public void getCurrentPlayer_givesCurrentPlayer() {
        Speler currentP = spel.getCurrentPlayer();
        Assertions.assertEquals(p1 ,currentP);
    }

    @Test
    public void getNextPlayer_givesNextPlayer() {
        spel.setNextPlayer();
        Speler currentP = spel.getCurrentPlayer();
        Assertions.assertEquals(p2 ,currentP);
    }

    @Test
    public void getPreviousPlayer_givesNextPlayer() {
        spel.setNextPlayer();
        Speler prevP = spel.getPreviousPlayer();
        Assertions.assertEquals(p1 ,prevP);
    }



}
/*


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