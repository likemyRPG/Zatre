package domein;

import java.util.List;

public class DomeinController{
	//region Variables
    SpelerRepository spelerRepository;
	PieceRepository pieceRepository;
	Speler speler;
	Spel spel;
	//endregion

	/**
	 * 				startSpel method is used to start a new game. It creates a new game and sets a random order for the players.
	 * 				it also subtracts a life from ech player.
	 */
	public void startSpel(){
		spelerRepository.shufflePlayers();
		spelerRepository.verminderSpeelkansen();
		spel = new Spel(spelerRepository.getSpelers());
	}

	/**
	 * Constructor for the DomeinController class.
	 * 				Sets the repositories for the players and the pieces.
	 */
	public DomeinController(){
		spelerRepository = new SpelerRepository();
		pieceRepository = new PieceRepository();
	}


	//region Player methods (Speler and SpelerRepository)
	/**
	 * @param gebruikersnaam is the username of the player.
	 * @param geboortejaar is the birthyear of the player.
	 * 				This method creates a new player.
	 */
	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		spelerRepository.registreerSpeler(gebruikersnaam, geboortejaar);
		speler = spelerRepository.getSpelers().get(spelerRepository.getSpelers().size() -1 );
	}

	/**
	 * @param gebruikersnaam is the username of the player.
	 * @param geboortejaar is the birthyear of the player.
	 * 				This method selects an existing player.
	 */
	public void selecteerSpeler(String gebruikersnaam, int geboortejaar) {
		spelerRepository.selecteerSpeler(gebruikersnaam, geboortejaar);
		speler = spelerRepository.getSpelers().get(spelerRepository.getSpelers().size() -1 );
	}


	/**
	 * @param gebruikersnaam is the username of the player.
	 * @param geboortejaar is the birthyear of the player.
	 * @return true if the player is already added.
	 * 				Method to check or a player with username and birthyear already exists in the list.
	 */
	public boolean alToegevoegd(String gebruikersnaam, int geboortejaar){
		return spelerRepository.alToegevoegd(gebruikersnaam, geboortejaar);
	}

	/**
	 * 				Method to get all the players
	 * @return a string with all the players.
	 */
	public String geefSpelers(){
		return spelerRepository.geefSpelers();
	}

	/**
	 * @return a string with all the lives of the players.
	 */
	public String geefSpelerKansen(){
		return spelerRepository.geefSpelersKansen();
	}


	/**
	 * @return the amount of current players.
	 */
	public int geefAantalSpelers(){
		return spelerRepository.getAantalSpelers();
	}

	/**
	 * @return a string that contains an overview of the player.
	 */
	public String geefOverzicht(){
		return speler.toString();
	}

	/**
	 * @return a String that contains the names of the players.
	 */
	public String geefSpelersNaam() {
		return spelerRepository.geefSpelersNaam();
	}

	/**
	 * 				Method to delete all the active players of the list.
	 */
	public void clearPlayers() {
		spelerRepository.clearPlayers();
	}

	/**
	 * 				Method to give a reward of 2 lives to the winner (Player with the highest score in the list).
	 */
	public void giveReward() {
		spel.giveReward();
	}

	/**
	 * @param position is the position of the player in the list leaderboard.
	 * @return the name of the player with the given position.
	 */
	public String getNameScoreBoardOnPosition(int position){
		return spel.getNameScoreBoardOnPosition(position);
	}


	/**
	 * @param username is the username of the player.
	 * @param birthyear is the birthyear of the player.
	 * 			This method checks the registration of the player.
	 */
	public void checkRegister(String username, Integer birthyear) {
		spelerRepository.checkRegister(username, birthyear);
	}

	/**
	 * @param i is the position of the player in the list.
	 * 			Method to delete the player of the list of active players
	 */
	public void verwijderSpeler(int i) {
		spelerRepository.verwijderSpeler(i);
	}

	//endregion

	//region Player methods (spel)
	// Method to get the leaderboard

	/**
	 * 						Method to determine the winner of the game, as well as the second and third place.
	 */
	public void determineWinner(){
		spel.determineWinner();
	}

	/**
	 * @return a string with username of the next player in the list of active players
	 */
	public String getNextPlayerUsername(){
		return spel.getUsernameNextPlayer();
	}

	/**
	 * Method to set the next player in the list of active players.
	 */
	public void setNextPlayer() {
		spel.setNextPlayer();
	}

	/**
	 * @return an integer with the score of the current player.
	 */
	public int getScoreCurrentPlayer(){ return spel.getScoreCurrentPlayer();}

	/**
	 * @return a String with the name of the current player
	 */
	public String getNameCurrentPlayer(){
		return spel.getNameCurrentPlayer();
	}
	//endregion

	//region Piece methods (PieceRepository)

	/**
	 * @param value the amount of piece.
	 * @return a List of integers with the values of the pieces.
	 * 					Method to get X amount of pieces from the piece repository
	 */
	public List<Integer> getRandomPieces(int value){
		return pieceRepository.giveRandomPieces(value);
	}

	// Method to add a piece to the piece repository
	/**
	 * @param value is the value of the piece.
	 *              Method to add a piece to the piece repository.
	 */
	public void voegPieceToe(int value) {
		pieceRepository.addPiece(new Piece(value));
	}

	/**
	 * @return an integer with the amount of pieces in the piece repository.
	 */
	public int geefAantalSteentjes(){ return pieceRepository.getAmountOfPieces();}

	/**
	 * Method to clear the list of own placed pieces
	 */
	public void clearOwnPieces() {
		spel.clearOwnPieces();
	}
	//endregion

	//region Game methods (Spel)
	// Check the placement of a piece on the board and return a boolean whether it is valid or not

	/**
	 * @param row is the row of the piece.
	 * @param column is the column of the piece.
	 * @param firstRound is a boolean to check if it is the first round.
	 * @param valueOfSelectedPiece is the value of the selected piece.
	 * @return a boolean whether the placement is valid or not.
	 *
	 *
	 */
	public boolean checkPlacement(int row, int column, boolean firstRound, int valueOfSelectedPiece){
		return spel.checkPlacement(row, column, firstRound, valueOfSelectedPiece);
	}

	/**
	 * @param row is the row of the piece.
	 * @param column is the column of the piece.
	 * @param valueOfSelectedPiece is the value of the selected piece.
	 * 				Method to set a value on the gameboard
	 */
	public void setValuesGameBoard(int row, int column, int valueOfSelectedPiece) {
		spel.setValuesGameBoard(row, column, valueOfSelectedPiece);
	}
	//endregion

	//region Calculate score methods (Spel)
	/**
	 * 				Method to calculate the score of the players (for testing)
	 */
	public void printScore() {
		spel.printScore();
	}


	/**
	 * @param row is the row of the piece.
	 * @param column is the column of the piece.
	 * @param valueOfSelectedPiece is the value of the selected piece.
	 *                             Method to calculate the score.
	 */
	public void calculateScore(int row, int column, int valueOfSelectedPiece) {
		spel.calculateScore(row, column, valueOfSelectedPiece);
	}

	/**
	 * @return a dimensional array with the scoreboard.
	 * 				Method to print the scoreboard in GUI.
	 */
	public int[][] printScoreBoard() {
		return spel.printScoreBoard();
	}
	//endregion

	//region Leaderboard methods (Spel)
	/**
	 * @param pathToFile is the path to the file.
	 *                   Method to create an image of the scoreboard in GUI.
	 */
	public void getLeaderboard(String pathToFile) {
		spel.makeScoreBoardImage(pathToFile);
	}

	/**
	 * @return a string with the name of the image.
	 *
	 */
	public String getImageName() {
		return spel.getImageName();
	}
	//endregion
}