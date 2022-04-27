package domein;

import java.util.ArrayList;
import java.util.List;

public class Spel {
    private List<Integer> randomPieces;
    private int[][] spelBord;
    private int[][] ownPieces;
    private List<Speler> currentPlayers;
    private byte currentPlayer = 0;

    int p10=0, p11=0, p12=0;
    boolean isDouble = false;

    public Spel(List<Speler> spelerList) {
        currentPlayers = new ArrayList<>(spelerList);
        spelBord = new int[15][15];
        ownPieces = new int[15][15];
        addNonUsableTiles();
    }

    //region Player Management
    public Speler setNextPlayer(){
        if(currentPlayers.size() -1 == currentPlayer) currentPlayer = 0;
        else currentPlayer++;
        return currentPlayers.get(currentPlayer);
    }

    public Speler getPreviousPlayer(){
        int index = currentPlayers.indexOf(currentPlayer);
        System.out.println(index);
        if(index == 0){
            return currentPlayers.get(currentPlayers.size() -1);
        }
        return currentPlayers.get(currentPlayer-1);
    }

    public Speler getNextPlayer(){
        int index = currentPlayers.indexOf(currentPlayer);
        if(index == currentPlayers.size() -1){
            return currentPlayers.get(0);
        }
        return currentPlayers.get(index+1);
    }

    public Speler getCurrentPlayer(){
        return currentPlayers.get(currentPlayer);
    }

    public void addScore(int round){
        currentPlayers.get(currentPlayer).getScoreblad().addScore(new Score(this.p10, this.p11, this.p12, round, this.isDouble));
        p10=0;
        p11=0;
        p12=0;
        isDouble = false;
    }

    public void printScore(){
        for(Speler speler : currentPlayers){
            System.out.printf("%s\t%s%n", speler.getGebruikersnaam(), speler.getScoreblad().getTotalScore());
        }
    }

    public Scoreblad getScoreBlad(){
        return currentPlayers.get(currentPlayer).getScoreblad();
    }
    //endregion

    //region Gameboard and pieces
    boolean checkPlacement(int row, int column, boolean firstRound, int valueOfSelectedPiece){
        return (valueOfSelectedPiece != 0 && !alreadyUsed(row, column) && allowedPlacement(row, column, firstRound) && allowedPlacementSum(row, column, valueOfSelectedPiece) && checkSpecialTileAllowed(row, column, valueOfSelectedPiece));
    }

    private boolean checkSpecialTileAllowed(int row, int column, int valueOfSelectedPiece) {
        if(checkSpecialTile(row, column)) {
            return ((sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) >= 10 || sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) == valueOfSelectedPiece) && (sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) >= 10 || sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) == valueOfSelectedPiece));
        }else return true;
    }

    private boolean checkSpecialTile(int row, int column) {
        if(row ==column) return true;
        else if((row == 0 || row == 14) && (column == 6 || column == 8))
            return true;
        else if((column == 0 || column == 14) && (row == 6 || row == 8))
            return true;
        return (column == 14-row);
    }

    private boolean allowedPlacementSum(int row, int column, int valueOfSelectedPiece) {
        return ((sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) <= 12 || sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) == valueOfSelectedPiece) && ((sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) <= 12) || sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) == valueOfSelectedPiece));
    }

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

    private boolean allowedPlacement(int row, int column, boolean firstRound) {
        if(column != 14 && (spelBord[row][column+1]!=0 && spelBord[row][column+1]!= 7 && (ownPieces[row][column+1] == 0 || firstRound)))
            return true;
        else if(column != 0 && (spelBord[row][column-1]!=0 && spelBord[row][column-1]!=7 && (ownPieces[row][column-1] == 0 || firstRound)))
            return true;
        else if(row !=14 && spelBord[row+1][column]!=0 && spelBord[row+1][column]!=7 && (ownPieces[row+1][column] == 0 || firstRound))
            return true;
        else return row != 0 && ( spelBord[row-1][column] != 0 && spelBord[row-1][column] != 7 && (ownPieces[row-1][column] == 0 || firstRound));
    }

    private boolean alreadyUsed(int row, int column){
        return spelBord[row][column] != 0;
    }

    //Add the value 7 to the tiles that are not usable (not existing)
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

    public void clearOwnPieces() {
        for (int col = 0; col < ownPieces.length; col++) {
            for (int row = 0; row < ownPieces.length; row++) {
                ownPieces[row][col] = 0;
            }
        }
    }

    public void setValuesGameBoard(int row, int column, int value) {
        spelBord[row][column] = value;
        ownPieces[row][column] = value;
    }

    public void printSpelBord(){
        for (int row = 0; row < spelBord.length; row++) {
            for (int col = 0; col < spelBord.length; col++) {
                System.out.print(spelBord[row][col] + " ");
            }
            System.out.println();
        }
    }

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

    public void calculateScore(int row, int column, int valueOfSelectedPiece, int round){
        int sumH = sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece);
        int sumV = sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece);
        if(sumH == 10 || sumV == 10) p10++;
        if(sumH == 11 || sumV == 11 ) p11++;
        if(sumH == 12 || sumV == 12) p12++;
        if(checkSpecialTile(row, column)) isDouble = true;
    }

    public int[][] getGameBoard() {
        return spelBord;
    }

    //endregion
}