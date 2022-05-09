package domein;

import java.util.List;

public class DomeinController{
	//region Variables
    SpelerRepository spelerRepository;
	PieceRepository pieceRepository;
	Speler speler;
	Spel spel;
	//endregion

	// StartSpel method creates a new Spel object and shuffles the players + substracts a life of every player
	public void startSpel(){
		spelerRepository.shufflePlayers();
		spelerRepository.verminderSpeelkansen();
		spel = new Spel(spelerRepository.getSpelers());
	}

	// Constructor
	public DomeinController(){
		spelerRepository = new SpelerRepository();
		pieceRepository = new PieceRepository();
	}

	//region Player methods (Speler and SpelerRepository)
	// Method to create a new player
	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		spelerRepository.registreerSpeler(gebruikersnaam, geboortejaar);
		speler = spelerRepository.getSpelers().get(spelerRepository.getSpelers().size() -1 );
	}

	// Method to select an already existing player
	public void selecteerSpeler(String gebruikersnaam, int geboortejaar) {
		spelerRepository.selecteerSpeler(gebruikersnaam, geboortejaar);
		speler = spelerRepository.getSpelers().get(spelerRepository.getSpelers().size() -1 );
	}

	// Method to check or a player with username and birthyear already exists
	public boolean alToegevoegd(String gebruikersnaam, int geboortejaar){
		return spelerRepository.alToegevoegd(gebruikersnaam, geboortejaar);
	}

	// Method to get all the players
	public String geefSpelers(){
		return spelerRepository.geefSpelers();
	}

	// Method to get all the lives of the players
	public String geefSpelerKansen(){
		return spelerRepository.geefSpelersKansen();
	}

	// Method to get the amount of active players
	public int geefAantalSpelers(){
		return spelerRepository.getAantalSpelers();
	}

	// To String method to get an overview of the player
	public String geefOverzicht(){
		return speler.toString();
	}

	// Method to get the name of the players
	public String geefSpelersNaam() {
		return spelerRepository.geefSpelersNaam();
	}

	// Method to delete all the active players of the list
	public void clearPlayers() {
		spelerRepository.clearPlayers();
	}

	// Method to give a reward to the winner
	public void giveReward(Speler speler) {
		spelerRepository.giveReward(speler);
	}

	// Method to check the registration of the player
	public void checkRegister(String text, Integer value) {
		spelerRepository.checkRegister(text, value);
	}

	// Method to delete the player of the list of active players
	public void verwijderSpeler(int i) {
		spelerRepository.verwijderSpeler(i);
	}
	//endregion

	//region Player methods (spel)
	// Method to get the leaderboard
	public List<Speler> determineWinner(){
		return spel.determineWinner();
	}

	// Method to get the next player in the list
	public Speler getNextPlayer(){
		return spel.getNextPlayer();
	}

	// Method to get the previous player in the list
	public Speler getPreviousPlayer(){
		return spel.getPreviousPlayer();
	}

	// Method to get the current player
	public Speler getCurrentPlayer() {
		return spel.getCurrentPlayer();
	}

	// Method to set the next player
	public void setNextPlayer() {
		spel.setNextPlayer();
	}
	//endregion

	//region Piece methods (PieceRepository)
	// Method to get X amount of pieces from the piece repository
	public List<Integer> getRandomPieces(int value){
		return pieceRepository.giveRandomPieces(value);
	}

	// Method to add a piece to the piece repository
	public void voegPieceToe(int value) {
		pieceRepository.addPiece(new Piece(value));
	}

	// Method to get the amount of pieces in the piece repository
	public int geefAantalSteentjes(){ return pieceRepository.getAmountOfPieces();}

	// Method to clear the list of own placed pieces
	public void clearOwnPieces() {
		spel.clearOwnPieces();
	}

	// NOT USED - Method to print the pieces in the piece repository (for testing)
	public String geefSteentjesWeer(){
		return pieceRepository.giveValues();
	}
	//endregion

	//region Game methods (Spel)
	// Check the placement of a piece on the board and return a boolean whether it is valid or not
	public boolean checkPlacement(int row, int column, boolean firstRound, int valueOfSelectedPiece){
		return spel.checkPlacement(row, column, firstRound, valueOfSelectedPiece);
	}

	// Method to set a value on the gameboard
	public void setValuesGameBoard(int row, int column, int valueOfSelectedPiece) {
		spel.setValuesGameBoard(row, column, valueOfSelectedPiece);
	}

	// NOT USED - Method to print the gameboard (for testing)
	public void printSpelBord() {
		spel.printSpelBord();
	}
	//endregion

	//region Calculate score methods (Spel)
	// NOT USED - Method to calculate the score of the players (for testing)
	public void printScore() {
		spel.printScore();
	}

	// Method to calculate the score of the player after a move
	public void calculateScore(int row, int column, int valueOfSelectedPiece) {
		spel.calculateScore(row, column, valueOfSelectedPiece);
	}

	// Method to print the scoreboard in GUI
	public int[][] printScoreBoard() {
		return spel.printScoreBoard();
	}
	//endregion

	//region Leaderboard methods (Spel)
	// Method to create a image of the scoreboard in GUI
	public void getLeaderboard(String pathToFile) {
		spel.makeScoreBoardImage(pathToFile);
	}

	// Method to get the name of the image that has been created
	public String getImageName() {
		return spel.getImageName();
	}
	//endregion
}