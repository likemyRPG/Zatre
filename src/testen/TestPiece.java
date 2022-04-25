package testen;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;

import domein.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


public class TestPiece {

    private Piece p;

    private static final int geldigValue = 4;

    @BeforeEach
    public void BeforeEach() {
        p = new Piece(geldigValue);
    }
    @Test
    public void maakPiece_geldigeValue_maaktPiece() {
        Assertions.assertEquals(p.getValue(),geldigValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-5,-1000,7,8,1000,10})
    public void maakPiece_ongeldigeValue_Exception(int v) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Piece(v);});
    }

}
