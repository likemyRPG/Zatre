package domein;

import exceptions.OutOfRangeException;

import java.util.List;

import domein.Piece;

public class DomeinController{

	SpelerRepository spelerRepository;
	PieceRepository pieceRepository;
	Speler speler;
	Spel spel;
	Piece piece;

	public DomeinController(){
		spelerRepository = new SpelerRepository();
		pieceRepository = new PieceRepository();
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

	public void startSpel(){
		spel = new Spel();
		spelerRepository.shufflePlayers();
		spelerRepository.verminderSpeelkansen();
	}

	public String geefSpelers(){
		return spelerRepository.geefSpelers();
	}

	public String geefSpelersNaam(){
		return spelerRepository.geefSpelersNaam();
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

	public String geefSteentjesWeer(){
		return pieceRepository.giveValues();
	}

	public int geefAantalSteentjes(){ return pieceRepository.getAmountOfPieces();}
}