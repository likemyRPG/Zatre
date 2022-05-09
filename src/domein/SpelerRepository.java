package domein;

import persistence.MyJDBC;
import persistence.language;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class SpelerRepository {

    //region Variables
    private MyJDBC sql;
    private List<Speler> spelers;
    private int aantalSpelers = 0;
    //endregion

    // Constructor
    public SpelerRepository() {
        // Een nieuwe list aanmaken van spelers
        spelers = new ArrayList<>();
    }

    // Method to check the register of a player (Check username, bithyear and check if the player is already registered)
    public void checkRegister(String username, int birthyear){
        if(username.length() < 5 || username.length() > 45) throw new IllegalArgumentException(language.rb.getString("minLengthUsername"));
        if(birthyear < 6 ) throw new IllegalArgumentException(language.rb.getString("minage"));
        if(alToegevoegd(username, birthyear)) throw new IllegalArgumentException("Speler is al toegevoegd!");
        registreerSpeler(username, birthyear);
    }

    // Method to register a new player and add it to the list
    public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
        aantalSpelers = spelers.size();
        // Make sure that the player limit is not reached
        if(aantalSpelers <= 3){
            // Check if the player is already registered
            boolean alBestaand = sql.zoekProfiel(gebruikersnaam, geboortejaar);
            // Throw Exception when the player is already registered
            if(alBestaand) throw new IllegalArgumentException(language.rb.getString("accountExists"));
            // Create a new player
            Speler speler = new Speler(gebruikersnaam, geboortejaar, 5);
            // Add the player to the list
            sql.maakProfiel(gebruikersnaam, geboortejaar, 5);
        }
        else throw new IllegalArgumentException("Het maximum aantal spelers is bereikt!");
    }

    // Method to add a player to the list (Login)
    public void selecteerSpeler(String gebruikersnaam, int geboortejaar) {
        aantalSpelers = spelers.size();
        if(aantalSpelers <= 3)
        {
            // Check or the account exists
            boolean alBestaand = sql.zoekProfiel(gebruikersnaam, geboortejaar);
            // When the account does not exist -> Throw Exception
            if(!alBestaand) throw new IllegalArgumentException(language.rb.getString("accReq"));
            // Get the amount of lives left for the player
            int aantalKansen = sql.getAantalKansenBestaandeSpeler(gebruikersnaam, geboortejaar);
            if(aantalKansen <= 0) {
                //When the player has no lives left --> Delete the player from the database
                sql.verwijderProfiel(gebruikersnaam, geboortejaar);
                //Throw An Exception when the player has no lives left
                throw new StringIndexOutOfBoundsException(language.rb.getString("noLives"));
            }else{
                // Create a new player
                Speler speler = new Speler(gebruikersnaam, geboortejaar, aantalKansen);
                // Add the player to the list
                spelers.add(speler);
            }
        }
        else throw new IllegalArgumentException("Het maximum aantal spelers is bereikt!");

    }

    // Method to shuffle the list of players
    public List<Speler> shufflePlayers(){
        Collections.shuffle(spelers);
        return spelers;
    }

    // Method to remove a life from the players
    public void verminderSpeelkansen(){
        for (Speler speler : spelers){
            speler.wijzigSpeelkansen();
            sql.verminderSpeelkansen(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getAantalKansen());
        }
    }

    // Method to get the names of the players and the amount of lives left in a string
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

    // Method to get the names of the players
    public String geefSpelersNaam() {
        if(spelers.isEmpty())
            return null;
        String resultaat = "";

        for(Speler gekozenSpelers : spelers)
            resultaat += String.format("%s%n", gekozenSpelers.getGebruikersnaam());
        return resultaat;
    }

    // Method to get the amount of lives left of the players
    public String geefSpelersKansen() {
        if(spelers.isEmpty())
            return null;
        String resultaat = "";

        for(Speler gekozenSpelers : spelers)
            resultaat += String.format("%4d❤%n", gekozenSpelers.getAantalKansen());
        return resultaat;
    }

    // Method to check if the player already is registered
    public boolean alToegevoegd(String gebruikersnaam, int geboortedatum){
        for(Speler speler : spelers){
            if(gebruikersnaam.equals(speler.getGebruikersnaam()) && speler.getGeboortejaar() == geboortedatum)
                return true;
        }
        return false;
    }

    // Method to give the reward to the player who won the game
    public void giveReward(Speler speler){
        speler.giveReward();
        sql.giveAward(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getAantalKansen());
    }

    // Method to get the list of players
    public List<Speler> getSpelers() {
        return spelers;
    }

    // Method to get the amount of players
    public int getAantalSpelers() {
        return spelers.size();
    }

    // Method to remove a player from the list
    public void verwijderSpeler(int i) {
        spelers.remove(i);
    }

    // Method to remove all the players from the list
    public void clearPlayers() {
        spelers.clear();
    }
}
