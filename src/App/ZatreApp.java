package App;

import domein.DomeinController;
import domein.Spel;
import domein.Speler;

import java.util.*;

public class ZatreApp {

    private DomeinController dc;
    Scanner myScanner = new Scanner(System.in);

    public ZatreApp(DomeinController dc) {
        this.dc = dc;
    }

    public void start(){
        dc.startSpel();
        verwelkoming();
        int keuze = inlogKeuze();
        if(keuze==1) spelerRegistreren();
        else spelerInloggen();
    }

    private void spelerInloggen() {
        System.out.println("Geef uw gebruikersnaam in: ");
        String gebruikersnaam = myScanner.next();
        System.out.println("Geef uw geboortejaar in: ");
        int geboortejaar = myScanner.nextInt();
        dc.selecteerSpeler(gebruikersnaam, geboortejaar);
        System.out.println(dc.geefOverzicht());
    }

    private void spelerRegistreren() {
        System.out.println("Geef een gebruikersnaam in: ");
        String gebruikersnaam = myScanner.next();
        System.out.println("Geef uw geboortejaar in: ");
        int geboortejaar = myScanner.nextInt();
        dc.registreerSpeler(gebruikersnaam, geboortejaar);
        System.out.println(dc.geefOverzicht());
    }

    private void verwelkoming(){
        System.out.println("Welkom bij Zatre!");
        System.out.println("------------------");
    }

    private int inlogKeuze(){
        int keuze;

        do{
            System.out.println("Maak een keuze om verder te gaan:");
            System.out.println("~1~ Registreren");
            System.out.println("~2~ Inloggen");
            keuze = myScanner.nextInt();
        }while(keuze > 2 || keuze < 1);

        return keuze;
    }
}
