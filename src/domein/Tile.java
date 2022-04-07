package domein;


public class Tile {
    private int x;
    private int y;
    private int value;
    private boolean isMerged;
    private boolean isEmpty;
    private boolean isNew;

    public Tile(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        isMerged = false;
        isEmpty = false;
        isNew = false;
    }



}
