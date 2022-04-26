package domein;

import exceptions.OutOfRangeException;

import java.util.List;

public class DomeinController{
    SpelerRepository spelerRepository;
	PieceRepository pieceRepository;
	Speler speler;
	Spel spel;

	public void startSpel(){
		spelerRepository.shufflePlayers();
		spelerRepository.verminderSpeelkansen();
		spel = new Spel(spelerRepository.getSpelers());
	}

	public DomeinController(){
		spelerRepository = new SpelerRepository();
		pieceRepository = new PieceRepository();
	}

	public boolean checkPlacement(int row, int column, boolean firstRound, int valueOfSelectedPiece){
		 return spel.checkPlacement(row, column, firstRound, valueOfSelectedPiece);
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) throws OutOfRangeException {
		spelerRepository.registreerSpeler(gebruikersnaam, geboortejaar);
		speler = spelerRepository.getSpelers().get(spelerRepository.getSpelers().size() -1 );
	}

	public void selecteerSpeler(String gebruikersnaam, int geboortejaar) throws OutOfRangeException {
		spelerRepository.selecteerSpeler(gebruikersnaam, geboortejaar);
		speler = spelerRepository.getSpelers().get(spelerRepository.getSpelers().size() -1 );
	}

	public boolean alToegevoegd(String gebruikersnaam, int geboortejaar){
		return spelerRepository.alToegevoegd(gebruikersnaam, geboortejaar);
	}

	public String geefSpelers(){
		return spelerRepository.geefSpelers();
	}

	public String geefSpelerKansen(){
		return spelerRepository.geefSpelersKansen();
	}

	public int geefAantalSpelers(){
		return spelerRepository.getAantalSpelers();
	}

	public String geefOverzicht(){
		return speler.toString();
	}

	public List<Integer> getRandomPieces(int value){
		return pieceRepository.giveRandomPieces(value);
	}

	public void voegPieceToe(int value) {
		pieceRepository.addPiece(new Piece(value));
	}

	public List<Speler> determineWinner(){
		return spel.determineWinner();
	}

	public String geefSteentjesWeer(){
		return pieceRepository.giveValues();
	}

	public int geefAantalSteentjes(){ return pieceRepository.getAmountOfPieces();}

	public void clearOwnPieces() {
		spel.clearOwnPieces();
	}

	public void setValuesGameBoard(int row, int column, int valueOfSelectedPiece) {
		spel.setValuesGameBoard(row, column, valueOfSelectedPiece);
	}

	public String geefSpelersNaam() {
		return spelerRepository.geefSpelersNaam();
	}

	public void printSpelBord() {
		spel.printSpelBord();
	}

	//region Players (Class: Spel)
	public Speler getNextPlayer(){
		return spel.getNextPlayer();
	}

	public Speler getPreviousPlayer(){
		return spel.getPreviousPlayer();
	}

	public Speler getCurrentPlayer() {
		return spel.getCurrentPlayer();
	}

	public void setNextPlayer() {
		spel.setNextPlayer();
	}

	public void printScore() {
		spel.printScore();
	}

	public void calculateScore(int row, int column, int valueOfSelectedPiece, int round) {
		spel.calculateScore(row, column, valueOfSelectedPiece, round);
	}

	public Scoreblad getScoreblad(){
		return spel.getScoreBlad();
	}

	public void addScore(int round){
		spel.addScore(round);
	}

	public int[][] getGameBoard() {
		return spel.getGameBoard();
	}
	//endregion
}