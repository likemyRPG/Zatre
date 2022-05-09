package domein;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spel {
    //region Variables
    private List<Integer> randomPieces;
    private int[][] spelBord;
    private int[][] ownPieces;
    private List<Speler> currentPlayers;
    private byte currentPlayer = 0;
    private String currentPlayerNameAndBirthYear, previousPlayerNameAndBirthYear;

    int p10=0, p11=0, p12=0;
    boolean isDouble = false;
    //endregion

    //region Spelers
    // Constructor for the spel class
    public Spel(List<Speler> spelerList) {
        currentPlayers = new ArrayList<>(spelerList);
        spelBord = new int[15][15];
        ownPieces = new int[15][15];
        addNonUsableTiles();
    }

    // Method to set the next player
    public Speler setNextPlayer(){
        if(currentPlayers.size() -1 == currentPlayer) currentPlayer = 0;
        else currentPlayer++;
        return currentPlayers.get(currentPlayer);
    }

    // Method to get the previous player
    public Speler getPreviousPlayer(){
        int index = currentPlayers.indexOf(currentPlayer);
        System.out.println(index);
        if(index == 0){
            return currentPlayers.get(currentPlayers.size() -1);
        }
        return currentPlayers.get(currentPlayer-1);
    }

    // Method to get the next player
    public Speler getNextPlayer(){
        int index = currentPlayers.indexOf(currentPlayer);
        if(index == currentPlayers.size() -1){
            return currentPlayers.get(0);
        }
        return currentPlayers.get(index+1);
    }

    // Method to get the current player
    public Speler getCurrentPlayer(){
        return currentPlayers.get(currentPlayer);
    }
    //endregion

    // Method to calculate the score that has to be added to the scoreboard
    public void calculateScore(int row, int column, int valueOfSelectedPiece){
        // Calculate the score of the piece that is being placed horizontally
        int sumH = sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece);
        // Calculate the score of the piece that is being placed vertically
        int sumV = sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece);
        if(sumH == 10) p10++;
        if(sumV == 10) p10++;
        if(sumH == 11) p11++;
        if(sumV == 11) p11++;
        if(sumH == 12) p12++;
        if(sumV == 12) p12++;
        // Check if there is a double score
        if(checkSpecialTile(row, column)) isDouble = true;
        // Add the score to the scoreboard using the medod addScore
        addScore();
    }

    // Method to add the score to the scoreboard after every placement of a piece
    public void addScore(){
        // When the scoreboard is empty, add the first score(row) to the scoreboard
        if(currentPlayers.get(currentPlayer).getScoreblad().getScores().isEmpty()){
            currentPlayers.get(currentPlayer).getScoreblad().addScore(new Score(this.p10, this.p11, this.p12, isDouble));
        }
        else{
            // When the scoreboard is not empty, check add the score to the scoreboard
            // By using a for loop, we can check what row of scores there is still a space for a new score of the type that is being placed
            for(Score s : currentPlayers.get(currentPlayer).getScoreblad().getScores()){
                if(p10!=0 || p11!=0 || p12!=0){
                    // Example: When there is still a space for a new score of type p10, add the score to the scoreboard at the row where there is still a space
                    if(p10 != 0 && (s.amountP10() == 0 )){
                        s.setP10(s.amountP10() + p10);
                        p10=0;
                    }else if (p11 != 0 && (s.amountP11() == 0 ) ) {
                        s.setP11(s.amountP11() + p11);
                        p11=0;
                    }else if(p12 != 0 && (s.amountP12() == 0 )){
                        s.setP12(s.amountP12() + p12);
                        p12=0;
                    }
                }
                // When there is a double score, add it to the scoreboard on the first spot that is free (Counting from up to down)
                if(isDouble && !s.isDoubleScore()){
                    s.setDoubleScore(true);
                    isDouble = false;
                }
            }
            // When there is no space for a new score, add a new row to the scoreboard
            if(p10!=0 || p11!=0 || p12!=0 || isDouble){
                currentPlayers.get(currentPlayer).getScoreblad().addScore(new Score(this.p10, this.p11, this.p12, isDouble));
            }

        }
        // Reset the variables to 0
        p10=0;
        p11=0;
        p12=0;
        isDouble = false;
        // Set the score of the scoreboard
        setScore();
    }

    // Method to set the score of the scoreboard
    public void setScore(){
        for (int i = 0; i < currentPlayers.size(); i++) {
            currentPlayers.get(i).getScoreblad().setScore();
        }
    }

    // Method to print the total score of the scoreboard
    public void printScore(){
        for(Speler speler : currentPlayers){
            System.out.printf("%s\t%s%n", speler.getGebruikersnaam(), speler.getScoreblad().getTotalScore());
        }
    }

    // NOT USED - Method that returns the ScoreBoard of the current player
    public Scoreblad getScoreBlad(){
        return currentPlayers.get(currentPlayer).getScoreblad();
    }

    // Method to print the scoreboard of the current player
    // returns an int[][] with the scoreboard
    public int[][] printScoreBoard(){
        // Create an 2d array of scores stored in ints
        int[][] scores = new int[currentPlayers.get(currentPlayer).getScoreblad().getScores().size()][6];
        for(Score s : currentPlayers.get(currentPlayer).getScoreblad().getScores()) {
            scores[currentPlayers.get(currentPlayer).getScoreblad().getScores().indexOf(s)][0] = s.isDoubleScore() ? 1 : 0;
            scores[currentPlayers.get(currentPlayer).getScoreblad().getScores().indexOf(s)][1] = s.amountP10();
            scores[currentPlayers.get(currentPlayer).getScoreblad().getScores().indexOf(s)][2] = s.amountP11();
            scores[currentPlayers.get(currentPlayer).getScoreblad().getScores().indexOf(s)][3] = s.amountP12();
            scores[currentPlayers.get(currentPlayer).getScoreblad().getScores().indexOf(s)][4] = s.getBonus();
            scores[currentPlayers.get(currentPlayer).getScoreblad().getScores().indexOf(s)][5] = s.getScore();
        }
        return scores;
    }

    // Method to check or a placement is valid or not, returns true or false
    boolean checkPlacement(int row, int column, boolean firstRound, int valueOfSelectedPiece){
        return (valueOfSelectedPiece != 0 && !alreadyUsed(row, column) && allowedPlacement(row, column, firstRound) && allowedPlacementSum(row, column, valueOfSelectedPiece) && checkSpecialTileAllowed(row, column, valueOfSelectedPiece));
    }

    // method to check if the tile is 'Special', if it is, check or the value of the placement Horizontally and Vertically is more than 10
    private boolean checkSpecialTileAllowed(int row, int column, int valueOfSelectedPiece) {
        if(checkSpecialTile(row, column)) {
            return ((sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) >= 10 || sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) == valueOfSelectedPiece) && (sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) >= 10 || sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) == valueOfSelectedPiece));
        }else return true;
    }

    // Method to check if the tile is special, returns true or false
    private boolean checkSpecialTile(int row, int column) {
        if(row ==7 && column == 7) return false;
        else if(row == column) return true;
        else if((row == 0 || row == 14) && (column == 6 || column == 8))
            return true;
        else if((column == 0 || column == 14) && (row == 6 || row == 8))
            return true;
        return (column == 14-row);
    }

    // Method to check if the placement is allowed (sum cant be higher than 12), returns true or false
    private boolean allowedPlacementSum(int row, int column, int valueOfSelectedPiece) {
        return ((sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) <= 12 || sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) == valueOfSelectedPiece) && ((sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) <= 12) || sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) == valueOfSelectedPiece));
    }

    // Method to calculate the value of the row horizontally, returns an int as value
    private int sumOfContinousFollowingValuesH(int row, int column, int valueOfSelectedPiece) {
        int sum1 = 0, sum2 = 0;
        int i = column;
        int j = row;
        spelBord[row][column] = valueOfSelectedPiece;
        while (spelBord[j][i] != 0 && spelBord[j][i] != 7) {
            sum1 += spelBord[j][i];
            if(i != 14)
                i++;
            else break;
        }
        i = column;
        j = row;
        while (spelBord[j][i] != 0 && spelBord[j][i] != 7) {
            sum2 += spelBord[j][i];
            if(i != 0)
                i--;
            else break;
        }
        spelBord[row][column] = 0;
        return (sum1 + sum2) - valueOfSelectedPiece;
    }

    // Method to calculate the value of the row vertically, returns an int as value
    private int sumOfContinousFollowingValuesV(int row, int column, int valueOfSelectedPiece) {
        int sum1 = 0, sum2 = 0;
        int i = column;
        int j = row;
        spelBord[row][column] = valueOfSelectedPiece;
        while (spelBord[j][i] != 0 && spelBord[j][i] != 7) {
            sum1 += spelBord[j][i];
            if(j != 14)
                j++;
            else break;
        }
        i = column;
        j = row;
        while (spelBord[j][i] != 0 && spelBord[j][i] != 7) {
            sum2 += spelBord[j][i];
            if(j != 0)
                j--;
            else break;
        }
        spelBord[row][column] = 0;
        return (sum1 + sum2) - valueOfSelectedPiece;
    }

    // Method to check if the placement is allowed (The tile has to be in the range of the board and cant be placed on the "not existing" tiles at the side of the gameboard),
    // returns true or false
    private boolean allowedPlacement(int row, int column, boolean firstRound) {
        if(column != 14 && (spelBord[row][column+1]!=0 && spelBord[row][column+1]!= 7 && (ownPieces[row][column+1] == 0 || firstRound)))
            return true;
        else if(column != 0 && (spelBord[row][column-1]!=0 && spelBord[row][column-1]!=7 && (ownPieces[row][column-1] == 0 || firstRound)))
            return true;
        else if(row !=14 && spelBord[row+1][column]!=0 && spelBord[row+1][column]!=7 && (ownPieces[row+1][column] == 0 || firstRound))
            return true;
        else return row != 0 && ( spelBord[row-1][column] != 0 && spelBord[row-1][column] != 7 && (ownPieces[row-1][column] == 0 || firstRound));
    }

    // Method to check if the placement is allowed (the tile can't be placed on a tile that is already placed), returns true or false
    private boolean alreadyUsed(int row, int column){
        return spelBord[row][column] != 0;
    }

    // Method to add the value 7 to the tiles that are not usable (not existing)
    private void addNonUsableTiles(){
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

    // Clear the list of ownpieces, this list is used to hold track of the pieces that are placed in one round by the same user
    // Its used to check if the user doesnt place a piece next to another piece that is placed by the same user
    public void clearOwnPieces() {
        for (int col = 0; col < ownPieces.length; col++) {
            for (int row = 0; row < ownPieces.length; row++) {
                ownPieces[row][col] = 0;
            }
        }
    }

    // Method to save the placement of the user in the lists spelBord and ownPieces
    public void setValuesGameBoard(int row, int column, int value) {
        spelBord[row][column] = value;
        ownPieces[row][column] = value;
    }

    // Method to print the gameboard (for debugging purposes)
    public void printSpelBord(){
        for (int row = 0; row < spelBord.length; row++) {
            for (int col = 0; col < spelBord.length; col++) {
                System.out.print(spelBord[row][col] + " ");
            }
        }
    }

    // Method to determine the winner of the game, and create the leaderboard
    public List<Speler> determineWinner(){
        //Get the player with the highest score
        List leaderBoard = new ArrayList<>();
        Speler firstPlace = null, secondPlace = null, thirdPlace = null;
        for (int i = 0; i < currentPlayers.size(); i++) {
            if(firstPlace == null || currentPlayers.get(i).getScoreblad().getTotalScore() > firstPlace.getScoreblad().getTotalScore()){
                Speler temp = null;
                if(firstPlace != null){
                    temp = secondPlace;
                    secondPlace = firstPlace;
                }
                firstPlace = currentPlayers.get(i);
               if(temp != null) thirdPlace = temp;
            }
            else if(secondPlace == null || currentPlayers.get(i).getScoreblad().getTotalScore() > secondPlace.getScoreblad().getTotalScore()){
                secondPlace = currentPlayers.get(i);
                if(thirdPlace!=null) thirdPlace = secondPlace;
            }else if(thirdPlace == null || currentPlayers.get(i).getScoreblad().getTotalScore() > thirdPlace.getScoreblad().getTotalScore()) thirdPlace = currentPlayers.get(i);
        }
        leaderBoard.add(firstPlace);
        leaderBoard.add(secondPlace);
        leaderBoard.add(thirdPlace);
        return leaderBoard;
    }

    // Method to get the gameboard
    public int[][] getGameBoard() {
        return spelBord;
    }

    // Method to create an image of the scoreboard
    public void makeScoreBoardImage(String pathToImage){
        BufferedImage image = new BufferedImage(1400, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 1400, 800);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 100));
        g2d.drawString("Scoreboard", 100, 100);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g2d.drawString("Player", 200, 200);
        g2d.drawString("Score", 800, 200);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 30));
        for (int i = 0; i < currentPlayers.size(); i++) {
            g2d.drawString(currentPlayers.get(i).getGebruikersnaam(), 200, 250 + i * 100);
            g2d.drawString(currentPlayers.get(i).getScoreblad().getTotalScore() + "", 800, 250 + i * 100);
        }
        try {
            ImageIO.write(image, "png", new File(pathToImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the generate a name for the leaderboard
    public String getImageName() {
        return "LeaderBoard" + System.currentTimeMillis() + ".png";
    }
}