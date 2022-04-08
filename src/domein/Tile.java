package domein;


public class Tile {
    private int[][] spelBord = new int[15][15];

    private void nonUsableTiles(){
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
