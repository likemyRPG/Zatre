package domein;

import domein.Speler;
import domein.Spel;
import exceptions.GeboortejaarBuitenBereikException;

public class DomeinController{

	SpelerRepository spelerRepository;
	Speler speler;
	Spel spel;

	public DomeinController(){
		spelerRepository = new SpelerRepository();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) throws GeboortejaarBuitenBereikException {
		spelerRepository.registreerSpeler(gebruikersnaam, geboortejaar);
		speler = spelerRepository.getSpelers().get(spelerRepository.getSpelers().size() -1 );
	}

	public void selecteerSpeler(String gebruikersnaam, int geboortejaar) throws GeboortejaarBuitenBereikException {
		spelerRepository.selecteerSpeler(gebruikersnaam, geboortejaar);
		speler = spelerRepository.getSpelers().get(spelerRepository.getSpelers().size() -1 );
	}

	public void startSpel(){
		spel = new Spel();
		spelerRepository.shufflePlayers();
		spelerRepository.verminderSpeelkansen();
	}

	public String geefSpelers(){
		return spelerRepository.geefSpelers();
	}
	public String geefOverzicht(){
		return speler.toString();
	}
}