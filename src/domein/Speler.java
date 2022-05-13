package domein;
import java.util.Calendar;
import java.util.ResourceBundle;
import persistence.language;

public class Speler {

    //region Variabelen
    private int geboortejaar;
    private String gebruikersnaam;
    private int aantalKansen;
    public static final int MIN_LEEFTIJD = 6;
    Scoreblad scoreblad;

    language ln = new language();
    ResourceBundle rb = ln.taal();
    //endregion

    //region Methodes

    /**
     * @param gebruikersnaam the username of the player.
     * @param geboortejaar the birthyear of the player.
     * @param aantalKansen the amount of lives left for the player.
     *                     Constructor for the Speler class.
     *                     Initializes the variables.
     *                     Initializes the scoreblad.
     */
    public Speler(String gebruikersnaam, int geboortejaar, int aantalKansen) {
        setGebruikersnaam(gebruikersnaam);
        setGeboortejaar(geboortejaar);
        this.aantalKansen = aantalKansen;
        scoreblad = new Scoreblad();
    }

    //endregion

    //region Getters And Setters

    /**
     * @param geboortejaar the birthyear of the player.
     *                     Sets the birthyear of the player.
     *                     Checks if the birthyear is valid.
     *                     Throws an exception when the birthyear is invalid.
     */
    public void setGeboortejaar(int geboortejaar) {
        // Het huidige jaar ophalen
        int jaar = Calendar.getInstance().get(Calendar.YEAR);
        // Exception wanneer het gebootejaar 0 is
        if (geboortejaar <= 0)
            throw new IllegalArgumentException(rb.getString("birthYearReq"));
        // Checken of de leeftijd van de speler groter is dan de minimum leeftijd
        if (geboortejaar > (jaar - MIN_LEEFTIJD))
        // -> Wanneer < MINIMUM => Throw Exception
            throw new IllegalArgumentException(rb.getString("minAge"));
        this.geboortejaar = geboortejaar;
    }

    /**
     * @param gebruikersnaam the username of the player.
     *                       Sets the username of the player.
     *                       Checks if the username is valid.
     *                       Throws an exception when the username is invalid.
     */
    public void setGebruikersnaam(String gebruikersnaam) {
        // Wanneer gebruikersnaam leeg of null is -> Throw exception
        if (gebruikersnaam == null || gebruikersnaam.isEmpty())
            throw new IllegalArgumentException(rb.getString("usernameReq"));
        // Wanneer gebruikersnaam minder dan 5 karakters bevat -> Throw exception
        if (gebruikersnaam.length() < 5)
            throw new IllegalArgumentException(rb.getString("minLengthUsername"));
        this.gebruikersnaam = gebruikersnaam;
    }

    /**
     * @return the birthyear of the player.
     */
    public int getGeboortejaar() {
        return this.geboortejaar;
    }

    /**
     * @return the username of the player.
     */
    public String getGebruikersnaam() {
        return this.gebruikersnaam;
    }

    /**
     * @return the scoreboard of the player.
     */
    public Scoreblad getScoreblad() {
        return this.scoreblad;
    }

    /**
     * @return the amount of lives left for the player.
     */
    public int getAantalKansen() {
        return this.aantalKansen;
    }

    /**
     * @return String representation of the player.
     *        Returns the username of the player.
     *        Returns the birthyear of the player.
     *        Returns the amount of lives left for the player.
     */
    @Override
    public String toString(){
        return String.format(rb.getString("userInfo"), this.gebruikersnaam, this.geboortejaar, this.aantalKansen);
    }

    /**
     * Subtracts 1 from the amount of lives left for the player.
     */
    public void wijzigSpeelkansen() {
        this.aantalKansen-=1;
    }

    /**
     * Add 2 lives to the amount of lives left for the player.
     */
    public void giveReward() {
        this.aantalKansen+=2;
    }
}
