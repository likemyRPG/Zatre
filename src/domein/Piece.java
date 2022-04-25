package domein;

public class Piece {
    private int value;

    public Piece(int value) {
        setValue(value);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        if (value < 1 || value > 6)
            throw new IllegalArgumentException();
        this.value = value;
    }
}
