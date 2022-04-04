package domein;

import exceptions.OutOfRangeException;

public class DomeinController{

	SpelerRepository spelerRepository;
	Speler speler;
	Spel spel;

	public DomeinController(){
		spelerRepository = new SpelerRepository();
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

}