package domein;

public class Piece {
    // Variables
    private int value;

    // Constructor
    public Piece(int value) {
        setValue(value);
    }

    // Method to get the value of the piece
    public int getValue() {
        return this.value;
    }

    // Method to set the value of the piece + check if the value is valid
    public void setValue(int value) {
        if (value < 1 || value > 6)
            throw new IllegalArgumentException();
        this.value = value;
    }
}
