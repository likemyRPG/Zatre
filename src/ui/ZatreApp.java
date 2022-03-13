package ui;

import domein.DomeinController;
import domein.Spel;
import domein.Speler;
import java.util.Calendar;

import java.util.*;

public class ZatreApp {

	private DomeinController dc;
	Scanner myScanner = new Scanner(System.in);

	public ZatreApp(DomeinController dc) {
		this.dc = dc;
	}

	public void start() {
		dc.startSpel();
		verwelkoming();

		int keuze = inlogKeuze();
		while (keuze != 3) {
			entryPoint(keuze);
			keuze = inlogKeuze();
		}
	}

	// methode voor registreren OF selecteren van speler
	private void entryPoint(int keuze) {
		String gebruikersnaam;
		int geboortejaar;
		int jaar = Calendar.getInstance().get(Calendar.YEAR);

		do {
			System.out.println("Geef uw gebruikersnaam in: ");
			gebruikersnaam = myScanner.next();
			if (gebruikersnaam.length() < 5)
				System.out.println("Gebruikersnaam moet minstens een lengte van 5 letters hebben.");
		} while (gebruikersnaam.length() < 5);

		do {
			System.out.println("Geef uw geboortejaar in: ");
			geboortejaar = myScanner.nextInt();
			if (geboortejaar < 1900 || geboortejaar > jaar - 6) System.out.println("Je moet minstens 6jaar oud zijn.");
		} while (geboortejaar < 1900 || geboortejaar > jaar - 6);

		if (keuze == 1)
			dc.registreerSpeler(gebruikersnaam, geboortejaar);
		else
			dc.selecteerSpeler(gebruikersnaam, geboortejaar);
		System.out.println(dc.geefOverzicht());
	}

	private void verwelkoming() {
		System.out.println("Welkom bij Zatre!");
		System.out.println("------------------");
	}

	private int inlogKeuze() {
		int keuze;

		do {
			System.out.println("Maak een keuze om verder te gaan:");
			System.out.println("~1~ Registreren");
			System.out.println("~2~ Inloggen");
			System.out.println("~3~ Stoppen");
			keuze = myScanner.nextInt();
		} while (keuze > 3 || keuze < 1);

		return keuze;
	}
}