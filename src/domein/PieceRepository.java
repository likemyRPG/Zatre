package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class PieceRepository {

    //region Variables
    private List<Piece> pieces;
    //endregion

    /**
     * Constructor for the PieceRepository class.
     */
    public PieceRepository() {
        // Een nieuwe list aanmaken van spelers
        pieces = new ArrayList<>();
        createPieces();
        shufflePieces();
    }

    /**
     * Method to create the pieces (Value 1-6) in the demanded amount of pieces + Add them to a list.
     */
    public void createPieces(){
        for (int i = 1; i <= 6; i++){
            for (int y = 1; y <= 20; y++){
                Piece piece = new Piece(i);
                pieces.add(piece);
            }
        }
        Piece piece = new Piece(1);
        pieces.add(piece);
    }

    /**
     * Method to shuffle the pieces.
     */
    public void shufflePieces(){
        Collections.shuffle(pieces);
    }

    /**
     * @param amount the amount of pieces to be returned.
     * @return the demanded amount of pieces.
     *            Method to get the demanded amount of pieces.
     */
    public List<Integer> giveRandomPieces(int amount)
    {
        //Get the first 3 value of the list pieces
        List<Piece> firstThree = new ArrayList<>();
        for (int i = 0; i < amount; i++)
        {
            firstThree.add(pieces.get(i));
        }
        //Remove the first 3 value of the list pieces
        pieces.removeAll(firstThree);

        //Add the value of firstThree to a list of integers
        List<Integer> randomPieces = new ArrayList<>();
        for (Piece piece : firstThree)
        {
            randomPieces.add(piece.getValue());
        }
        return randomPieces;
    }

    /**
     * @param piece the piece to be added.
     *              Method to add a piece to the piece repository.
     */
    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    /**
     * @return a String with all the values of the pieces.
     *            Method to get all the values of the pieces.
     */
    public String giveValues() {
        // Check if the list is empty
        if (pieces.isEmpty())
            return String.format("There are no pieces left");
        String resultaat = "";
        // Add the values of the pieces to the string
        for (Piece existingPieces : pieces)
            resultaat += String.format("%4d%n", existingPieces.getValue());
        // Return the string
        return resultaat;
    }

    /**
     * @return a List with all the pieces.
     *           Method to get all the pieces.
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * @return an integer with the amount of pieces in the piece repository.
     *           Method to get the amount of pieces in the piece repository.
     */
    public int getAmountOfPieces() {
        return pieces.size();
    }
}