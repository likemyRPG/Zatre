package domein;
import persistence.SQLCommands;
import persistence.MyJDBC;
import java.util.Calendar;
import java.util.ResourceBundle;
import persistence.language;

public class Speler {

    private MyJDBC sql;
    private int geboortejaar;
    private String gebruikersnaam;
    private int aantalKansen;
    public static final int MIN_LEEFTIJD = 6;

    language ln = new language();
    ResourceBundle rb = ln.taal();


    public Speler(){

    }

    // Nieuwe speler aanmaken
    public void registreerSpeler(String gebruikersnaam, int geboortejaar){
        setGeboortejaar(geboortejaar);
        setGebruikersnaam(gebruikersnaam);
        // Aantalkansen standaard gelijk zetten met 5
        this.aantalKansen = 5;
        // Controleren of de gebruiker (gebruikersnaam en geboortedatum) al reeds bestaad
        controleerUniekheid();
        // Een profiel aanmaken in de database
        sql.maakProfiel(this.gebruikersnaam, this.geboortejaar, this.aantalKansen);
    }
    // Een speler selecteren
    public void selecteerSpeler(String gebruikersnaam, int geboortejaar){
        // Controleren of speler wel degelijk een account heeft
        boolean alBestaand = sql.zoekProfiel(gebruikersnaam, geboortejaar);
        // Zo niet -> Throw Exception
        if(!alBestaand) throw new IllegalArgumentException(rb.getString("accReq"));
        // Aantalkansen uit de database halen
        setGebruikersnaam(gebruikersnaam);
        setGeboortejaar(geboortejaar);
        this.aantalKansen = sql.getAantalKansenBestaandeSpeler(gebruikersnaam, geboortejaar);
    }

    // Setter voor geboortejaar
    public void setGeboortejaar(int geboortejaar) {
        // Het huidige jaar ophalen
        int jaar = Calendar.getInstance().get(Calendar.YEAR);
        // Exception wanneer het gebootejaar 0 is
        if(geboortejaar <= 0)
            throw new IllegalArgumentException(rb.getString("birthYearReq"));
        // Checken of de leeftijd van de speler groter is dan de minimum leeftijd
        else if(geboortejaar > (jaar-MIN_LEEFTIJD))
            // -> Wanneer < MINIMUM => Throw Exception
            throw new IllegalArgumentException(String.format(rb.getString("minAge")));
        this.geboortejaar = geboortejaar;
    }

    // Setter voor gebruikersnaam
    public void setGebruikersnaam(String gebruikersnaam) {
        // Wanneer gebruikersnaam leeg of null is -> Throw exception
        if(gebruikersnaam == null || gebruikersnaam.isEmpty())
            throw new IllegalArgumentException(rb.getString("usernameReq"));
            // Wanneer gebruikersnaam minder dan 5 karakters bevat -> Throw exception
        else if(gebruikersnaam.length() < 5)
            throw new IllegalArgumentException(rb.getString("minLengthUsername"));
        this.gebruikersnaam = gebruikersnaam;
    }
    // Functie om te controleren of een speler al bestaat
    // Throw Exception wanneer het een bestaande speler is
    public void controleerUniekheid(){
        boolean alBestaand = sql.zoekProfiel(this.gebruikersnaam,this.geboortejaar);
        if(alBestaand) throw new IllegalArgumentException(rb.getString("accountExists"));
    }
    // Getter geboortejaar voor testen
    public int getGeboortejaar() {
        return this.geboortejaar;
    }

    // Getter gebruikersnaam voor testen
    public String getGebruikersnaam() {
        return this.gebruikersnaam;
    }

    // Getter aantalKansen
    public int getAantalKansen() {
        return this.aantalKansen;
    }

    // toString methode voor het afprinten van info van de gebruiker
    @Override
    public String toString(){
        return String.format("Actieve speler met gebruikersnaam \"%s\" en geboortedatum %d heeft %d speelkansen.%n", this.gebruikersnaam, this.geboortejaar, this.aantalKansen);
    }
}
