package domein;
import java.io.IOException;
import java.util.Calendar;
import java.util.ResourceBundle;
import persistence.language;

public class Speler {

    //region Variabelen
    private int geboortejaar;
    private String gebruikersnaam;
    private int aantalKansen;
    private boolean[][] score;
    public static final int MIN_LEEFTIJD = 6;
    Scoreblad scoreblad;

    language ln = new language();
    ResourceBundle rb = ln.taal();
    //endregion

    //region Methodes
    public Speler(String gebruikersnaam, int geboortejaar, int aantalKansen) {
        setGebruikersnaam(gebruikersnaam);
        setGeboortejaar(geboortejaar);
        this.aantalKansen = aantalKansen;
        scoreblad = new Scoreblad();
    }

    //endregion

    //region Getters And Setters

    // Setter voor geboortejaar
    public void setGeboortejaar(int geboortejaar) {
        // Het huidige jaar ophalen
        int jaar = Calendar.getInstance().get(Calendar.YEAR);
        // Exception wanneer het gebootejaar 0 is
        if (geboortejaar <= 0)
            throw new IllegalArgumentException(rb.getString("birthYearReq"));
        // Checken of de leeftijd van de speler groter is dan de minimum leeftijd
        if (geboortejaar > (jaar - MIN_LEEFTIJD))
        // -> Wanneer < MINIMUM => Throw Exception
            throw new IllegalArgumentException(String.format(rb.getString("minAge")));
        this.geboortejaar = geboortejaar;
    }

    // Setter voor gebruikersnaam
    public void setGebruikersnaam(String gebruikersnaam) {
        // Wanneer gebruikersnaam leeg of null is -> Throw exception
        if (gebruikersnaam == null || gebruikersnaam.isEmpty())
            throw new IllegalArgumentException(rb.getString("usernameReq"));
        // Wanneer gebruikersnaam minder dan 5 karakters bevat -> Throw exception
        if (gebruikersnaam.length() < 5)
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

    // Getter scoreblad
    public Scoreblad getScoreblad() {
        return this.scoreblad;
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

    public void setScoreblad(Scoreblad scoreblad) {

    }

    public void giveReward() {
        this.aantalKansen+=2;
    }
    //endregion
}
