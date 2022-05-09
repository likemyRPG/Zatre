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
    // Constructor
    public Speler(String gebruikersnaam, int geboortejaar, int aantalKansen) {
        setGebruikersnaam(gebruikersnaam);
        setGeboortejaar(geboortejaar);
        this.aantalKansen = aantalKansen;
        scoreblad = new Scoreblad();
    }

    //endregion

    //region Getters And Setters

    // Method to set the birthyear of the player (Check if the player is old enough and the if the birthyear is valid)
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

    // Method to set the username of the player (Check if the username is valid)
    public void setGebruikersnaam(String gebruikersnaam) {
        // Wanneer gebruikersnaam leeg of null is -> Throw exception
        if (gebruikersnaam == null || gebruikersnaam.isEmpty())
            throw new IllegalArgumentException(rb.getString("usernameReq"));
        // Wanneer gebruikersnaam minder dan 5 karakters bevat -> Throw exception
        if (gebruikersnaam.length() < 5)
            throw new IllegalArgumentException(rb.getString("minLengthUsername"));
        this.gebruikersnaam = gebruikersnaam;
    }

    // Method to get the birthyear of the player (Testing purposes)
    public int getGeboortejaar() {
        return this.geboortejaar;
    }

    // Method to get the username of the player
    public String getGebruikersnaam() {
        return this.gebruikersnaam;
    }

    // Method to get the scoreboard of the player
    public Scoreblad getScoreblad() {
        return this.scoreblad;
    }

    // Method to get the amount of lives the player has
    public int getAantalKansen() {
        return this.aantalKansen;
    }

    // To String Method
    @Override
    public String toString(){
        return String.format(rb.getString("userInfo"), this.gebruikersnaam, this.geboortejaar, this.aantalKansen);
    }

    // Method to subtract a life from the player
    public void wijzigSpeelkansen() {
        this.aantalKansen-=1;
    }

    // Method to add 2 lives to the winner
    public void giveReward() {
        this.aantalKansen+=2;
    }
}
