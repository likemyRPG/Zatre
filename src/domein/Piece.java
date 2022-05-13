package domein;

public class Piece {
    // Variables
    private int value;

    /**
     * @param value the value of the piece.
     *              Constructor for the piece.
     */
    public Piece(int value) {
        setValue(value);
    }

    /**
     * @return the value of the piece.
     *             Method to get the value of the piece.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @param value the value of the piece.
     *              Method to set the value of the piece.
     */
    public void setValue(int value) {
        if (value < 1 || value > 6)
            throw new IllegalArgumentException();
        this.value = value;
    }
}
