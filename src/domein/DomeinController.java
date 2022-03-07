package domein;

import domein.Speler;
import domein.Spel;

public class DomeinController{

	private Speler speler;
	private Spel spel;

	public void startSpel(){
		spel = new Spel();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar){
		speler = new Speler(gebruikersnaam, geboortejaar);
	}

	public void selecteerSpeler(String gebruikersnaam, int geboortejaar){
		speler.selecteerSpeler(gebruikersnaam, geboortejaar);
	}

	public String geefOverzicht(){
		return speler.toString();
	}

}