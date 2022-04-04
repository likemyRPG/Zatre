package domein;

import exceptions.OutOfRangeException;
import persistence.MyJDBC;
import persistence.language;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.ResourceBundle;

public class SpelerRepository {

    private MyJDBC sql;
    private List<Speler> spelers;
    private int aantalSpelers = 0;

    public SpelerRepository() {
        // Een nieuwe list aanmaken van spelers
        spelers = new ArrayList<>();
    }

    public void registreerSpeler(String gebruikersnaam, int geboortejaar) throws OutOfRangeException {
        aantalSpelers = spelers.size();
        if(aantalSpelers <= 3){
            // Controleren of een speler al bestaat
            boolean alBestaand = sql.zoekProfiel(gebruikersnaam, geboortejaar);
            // Throw Exception wanneer het een bestaande speler is
            if(alBestaand) throw new IllegalArgumentException(language.rb.getString("accountExists"));
            // Een nieuwe speler aanmaken
            Speler speler = new Speler(gebruikersnaam, geboortejaar, 5);
            // Speler toevoegen aan de database
            sql.maakProfiel(gebruikersnaam, geboortejaar, 5);
            spelers.add(speler);
        }
        else throw new IllegalArgumentException("Het maximum aantal spelers is bereikt!");
    }

    public void selecteerSpeler(String gebruikersnaam, int geboortejaar) throws OutOfRangeException {
        aantalSpelers = spelers.size();
        if(aantalSpelers <= 3)
        {
            // Controleren of speler wel degelijk een account heeft
            boolean alBestaand = sql.zoekProfiel(gebruikersnaam, geboortejaar);
            // Zo niet --> Throw Exception
            if(!alBestaand) throw new IllegalArgumentException(language.rb.getString("accReq"));
            // Aantal kansen uit de database halen
            int aantalKansen = sql.getAantalKansenBestaandeSpeler(gebruikersnaam, geboortejaar);
            // Een nieuwe speler aanmaken
            Speler speler = new Speler(gebruikersnaam, geboortejaar, aantalKansen);
            // De speler toevoegen aan de lijst van spelers
            spelers.add(speler);
        }
        else throw new IllegalArgumentException("Het maximum aantal spelers is bereikt!");

    }

    public List<Speler> shufflePlayers(){
        Collections.shuffle(spelers);
        return spelers;
    }

    public void verminderSpeelkansen(){
        for (Speler speler : spelers){
            speler.wijzigSpeelkansen();
            sql.verminderSpeelkansen(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getAantalKansen());
        }
    }

    public String geefSpelers() {
        // Checken of de lijst van spelers leeg is, wanneer deze leeg is krijg je een gepast bericht
        if(spelers.isEmpty())
            return String.format(language.rb.getString("noPlayersYet"));
        // String aanmaken voor alle spelers te returnen
        String resultaat = "";
        // De spelers toevoegen aan de string
        for(Speler gekozenSpelers : spelers)
            resultaat += String.format("%s\t\t%4d%n", gekozenSpelers.getGebruikersnaam(), gekozenSpelers.getAantalKansen());
        // De String returnen
        return resultaat;
    }

    public String geefSpelersNaam() {
        if(spelers.isEmpty())
            return String.format(language.rb.getString("noPlayersYet"));
        String resultaat = "";

        for(Speler gekozenSpelers : spelers)
            resultaat += String.format("%s%n", gekozenSpelers.getGebruikersnaam());
        return resultaat;
    }

    public String geefSpelersKansen() {
        if(spelers.isEmpty())
            return null;
        String resultaat = "";

        for(Speler gekozenSpelers : spelers)
            resultaat += String.format("%4d%n", gekozenSpelers.getAantalKansen());
        return resultaat;
    }

    public boolean alToegevoegd(String gebruikersnaam, int geboortedatum){
        for(Speler speler : spelers){
            if(gebruikersnaam.equals(speler.getGebruikersnaam()) && speler.getGeboortejaar() == geboortedatum)
                return true;
        }
        return false;
    }

    public List<Speler> getSpelers() {
        return spelers;
    }

    public int getAantalSpelers() {
        return spelers.size();
    }
}
