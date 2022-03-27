package domein;
import java.util.Calendar;
import java.util.ResourceBundle;

import exceptions.GeboortejaarBuitenBereikException;
import persistence.language;

public class Speler {

    //region Variabelen
    private int geboortejaar;
    private String gebruikersnaam;
    private int aantalKansen;
    public static final int MIN_LEEFTIJD = 6;

    language ln = new language();
    ResourceBundle rb = ln.taal();
    //endregion

    //region Methodes
    public Speler(String gebruikersnaam, int geboortejaar, int aantalKansen) throws GeboortejaarBuitenBereikException {
        setGebruikersnaam(gebruikersnaam);
        setGeboortejaar(geboortejaar);
        this.aantalKansen = aantalKansen;
    }
    //endregion

    //region Getters And Setters

    // Setter voor geboortejaar
    public void setGeboortejaar(int geboortejaar) throws GeboortejaarBuitenBereikException {
        // Het huidige jaar ophalen
        int jaar = Calendar.getInstance().get(Calendar.YEAR);
        // Exception wanneer het gebootejaar 0 is
        if(geboortejaar <= 0)
            throw new GeboortejaarBuitenBereikException(String.format(rb.getString("geboorteException")));
        // Checken of de leeftijd van de speler groter is dan de minimum leeftijd
        else if(geboortejaar < 1900||geboortejaar > (jaar-MIN_LEEFTIJD))
            // -> Wanneer < MINIMUM => Throw Exception
            throw new GeboortejaarBuitenBereikException(String.format(rb.getString("geboorteException")));
            // throw new IllegalArgumentException(String.format(rb.getString("minAge")));
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

    //endregion

    //region toString methode
    @Override
    public String toString(){
        return String.format(rb.getString("userInfo"), this.gebruikersnaam, this.geboortejaar, this.aantalKansen);
    }

    public void wijzigSpeelkansen() {
        this.aantalKansen-=1;
    }
    //endregion
}
