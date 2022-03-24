package domein;

import persistence.MyJDBC;
import persistence.language;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SpelerRepository {

    private MyJDBC sql;
    private List<Speler> spelers;

    // Variabele aanmaken voor de verschillende talen

    // language ln = new language();
    // ResourceBundle rb = ln.taal();

    public SpelerRepository() {
        // Een nieuwe list aanmaken van spelers
        spelers = new ArrayList<>();
    }

    public void registreerSpeler(String gebruikersnaam, int geboortejaar){
        // Controleren of een speler al bestaat
        boolean alBestaand = sql.zoekProfiel(gebruikersnaam, geboortejaar);
        // Throw Exception wanneer het een bestaande speler is
        if(alBestaand) throw new IllegalArgumentException(language.rb.getString("accountExists"));
        // Een nieuwe speler aanmaken
        Speler speler = new Speler(gebruikersnaam, geboortejaar, 5);
        // Speler toevoegen aan de database
        sql.maakProfiel(gebruikersnaam, geboortejaar, 5);
    }

    public void selecteerSpeler(String gebruikersnaam, int geboortejaar){
        // Controleren of speler wel degelijk een account heeft
        boolean alBestaand = sql.zoekProfiel(gebruikersnaam, geboortejaar);
        // Zo niet --> Throw Exception
        if(!alBestaand) throw new IllegalArgumentException(language.rb.getString("accReq"));

        // Aaatal kansen uit de database halen
        int aantalKansen = sql.getAantalKansenBestaandeSpeler(gebruikersnaam, geboortejaar);
        // Een nieuwe speler aanmaken
        Speler speler = new Speler(gebruikersnaam, geboortejaar, aantalKansen);
        // De speler toevoegen aan de lijst van spelers
        spelers.add(speler);
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

    public List<Speler> getSpelers() {
        return spelers;
    }
}
