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
        setDefaultValue();
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

    private void setDefaultValue(){
        spelBord[0][0]=7;
        spelBord[0][1]=7;
        spelBord[0][2]=7;
        spelBord[0][3]=7;
        spelBord[0][7]=7;
        spelBord[0][11]=7;
        spelBord[0][12]=7;
        spelBord[0][13]=7;
        spelBord[0][14]=7;
        spelBord[1][0]=7;
        spelBord[1][14]=7;
        spelBord[2][0]=7;
        spelBord[2][14]=7;
        spelBord[3][0]=7;
        spelBord[3][14]=7;
        spelBord[7][0]=7;
        spelBord[7][14]=7;
        spelBord[11][0]=7;
        spelBord[11][14]=7;
        spelBord[12][0]=7;
        spelBord[12][14]=7;
        spelBord[13][0]=7;
        spelBord[13][14]=7;
        spelBord[14][0]=7;
        spelBord[14][1]=7;
        spelBord[14][2]=7;
        spelBord[14][3]=7;
        spelBord[14][7]=7;
        spelBord[14][11]=7;
        spelBord[14][12]=7;
        spelBord[14][13]=7;
        spelBord[14][14]=7;
    }
}

