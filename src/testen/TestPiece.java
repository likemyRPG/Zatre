package testen;
import domein.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class TestPiece {

    private Piece p;

    private static final int GELDIGE_VALUE = 4;

    @BeforeEach
    public void BeforeEach() {
        p = new Piece(GELDIGE_VALUE);
    }
    @Test
    public void maakPiece_getValue_returnedValue() {
    	int val = p.getValue();
        Assertions.assertEquals(val,GELDIGE_VALUE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-5,-1000,7,8,1000,10})
    public void maakPiece_ongeldigeValue_Exception(int v) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Piece(v);});
    }

}
