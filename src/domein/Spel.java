package domein;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Spel {
    int[][] spelBord = new int[15][15];
    private List<Integer> randomPieces;
    List<Integer> stenen= new ArrayList();

    public Spel() {

    }

    public List<Integer> getRandomPieces(int value) {
        randomPieces = new ArrayList<>();
        //Get 3 random ints between 1 and 6 and add to the List
        for (int i = 0; i < 3; i++) {
            randomPieces.add((int) (Math.random() * value) + 1);
        }
        return randomPieces;
    }
    // De "ontzichtbare" vakjes krijgen de waarde 7

}

