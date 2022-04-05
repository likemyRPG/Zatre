package domein;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Spel {
    int[][] spelBord = new int[15][15];
    List<Integer> stenen= new ArrayList();

    public Spel() {
        //Maak het spel aan geeft de ontzichtbare vakjes de waarde 7
        setDefaultValue();
        maakSteentjesAan();
        /*for (int aNumber : stenen ) {
            System.out.println( aNumber );
        }
        //Print het bord af
            for (int row = 0; row < spelBord.length; row++)// Door de rijen gaan
            {
                for (int col = 0; col < spelBord[row].length; col++)// Door de kolommen gaan
                {
                    System.out.printf("%3d", spelBord[row][col]);
                }
                System.out.println(); // Print een nieuwe rij af
            }*/
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

    private void maakSteentjesAan(){
        stenen.add(1);
        for (int i = 1; i <= 6; i++){
            for (int y = 0; y <= 21; y++){
                stenen.add(i);
            }
        }
        Collections.shuffle(stenen);
    }
}

