package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import domein.Speler;


class SpelerTest {

    static final String gebruikersnaam = "testGebruiker";
    static final int geboortedatum = 2003;
    private Speler speler;
    @BeforeEach
    public void BeforeEach() {
        // speler = new Speler();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void maakSpelerAan_FoutieveNaam(String gebruikersnaam) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {speler.setGebruikersnaam(gebruikersnaam);});
    }

    @ParameterizedTest
    @ValueSource(ints = {2020, 2021, 2017})
    public void speler_MaakEenSpelerAan_FoutieveLeeftijd_ThrowException(int geboortedatum) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {speler.setGeboortejaar(geboortedatum);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "User", "AbCd"})
    public void speler_MaakEenSpelerAan_Foutievenaam_naamTeKort_ThrowException(String gebruikersnaam) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {speler.setGebruikersnaam(gebruikersnaam);});
    }
    @ParameterizedTest
    @ValueSource(strings = {"zweeduizenddrie", "idk", "test"})
    public void speler_MaakEenSpelerAan_EnkelCijfers_ThrowException(int geboortedatum) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {speler.setGeboortejaar(geboortedatum);});
    }
}
