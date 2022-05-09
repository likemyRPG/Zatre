package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class PieceRepository {

    //region Variables
    private List<Piece> pieces;
    //endregion

    // Constructor
    public PieceRepository() {
        // Een nieuwe list aanmaken van spelers
        pieces = new ArrayList<>();
        createPieces();
        shufflePieces();
    }

    // method to create the pieces (Value 1-6) in the demanded amount of pieces + Add them to a list
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

    // Method to shuffle the pieces
    public void shufflePieces(){
        Collections.shuffle(pieces);
    }

    // Method that returns the values of an X amount of pieces from the list and removes them from the list
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

    // Method to add a piece to the list
    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    // Method to get the list of pieces (String)
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

    // Method to get the list of pieces (Piece)
    public List<Piece> getPieces() {
        return pieces;
    }

    // Method that returns the amount of pieces left
    public int getAmountOfPieces() {
        return pieces.size();
    }
}