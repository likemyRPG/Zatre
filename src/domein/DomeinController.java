package domein;

import domein.Speler;
import domein.Spel;

import javax.swing.plaf.SeparatorUI;

public class DomeinController{

	SpelerRepository spelerRepository;
	Speler speler;

	public DomeinController(){
		spelerRepository = new SpelerRepository();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar){
		spelerRepository.registreerSpeler(gebruikersnaam, geboortejaar);
	}

	public void selecteerSpeler(String gebruikersnaam, int geboortejaar){
		spelerRepository.selecteerSpeler(gebruikersnaam, geboortejaar);
	}


	public String geefSpelers(){
		return spelerRepository.geefSpelers();
	}
	public String geefOverzicht(){
		return speler.toString();
	}

	public void startSpel() {
		speler = new Speler();
	}
}