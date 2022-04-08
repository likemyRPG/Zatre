package domein;

import exceptions.OutOfRangeException;
import persistence.MyJDBC;
import persistence.language;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.ResourceBundle;

public class PieceRepository {

    private List<Piece> pieces;

    public PieceRepository() {
        // Een nieuwe list aanmaken van spelers
        pieces = new ArrayList<>();
        createPieces();
        shufflePieces();
    }

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

    public void shufflePieces(){
        Collections.shuffle(pieces);
    }

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

    public String giveValues() {
        // Checken of de lijst van spelers leeg is, wanneer deze leeg is krijg je een gepast bericht
        if (pieces.isEmpty())
            return String.format("There are no pieces left");
        // String aanmaken voor alle spelers te returnen
        String resultaat = "";
        // De spelers toevoegen aan de string
        for (Piece existingPieces : pieces)
            resultaat += String.format("%4d%n", existingPieces.getValue());
        // De String returnen
        return resultaat;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public int getAmountOfPieces() {
        return pieces.size();
    }
}
