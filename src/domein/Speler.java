package domein;
import persistence.SQLCommands;
import persistence.MyJDBC;
import java.util.Calendar;
public class Speler {

    private MyJDBC sql;
    private int geboortejaar;
    private String gebruikersnaam;
    private int aantalKansen;
    public static final int MIN_LEEFTIJD = 6;


    public Speler(String gebruikersnaam, int geboortejaar){
        setGeboortejaar(geboortejaar);
        setGebruikersnaam(gebruikersnaam);
        this.aantalKansen = 5;
        controleerUniekheid();
        sql.maakProfiel(this.gebruikersnaam, this.geboortejaar, this.aantalKansen);
    }

    public void selecteerSpeler(String gebruikersnaam, int geboortejaar){
        boolean alBestaand = sql.zoekProfiel(this.gebruikersnaam, this.geboortejaar);
        if(!alBestaand) throw new IllegalArgumentException("Dit account bestaat nog niet!");
        this.aantalKansen = sql.getAantalKansenBestaandeSpeler(gebruikersnaam, geboortejaar);
    }

    public void setGeboortejaar(int geboortejaar) {
        int jaar = Calendar.getInstance().get(Calendar.YEAR);

        if(geboortejaar <= 0)
            throw new IllegalArgumentException("Geboortejaar moet ingevuld zijn!");
        else if(geboortejaar > (jaar-MIN_LEEFTIJD))
            throw new IllegalArgumentException(String.format("De minimum leeftijd is %d jaar!", MIN_LEEFTIJD));
        this.geboortejaar = geboortejaar;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        if(gebruikersnaam == null || gebruikersnaam.isEmpty())
            throw new IllegalArgumentException("Gebruikersnaam moet ingevuld zijn!");
        else if(gebruikersnaam.length() < 5)
            throw new IllegalArgumentException("Gebruikersnaam moet minstens een lengte van 5 letters hebben...");
        this.gebruikersnaam = gebruikersnaam;
    }

    public void controleerUniekheid(){
        boolean alBestaand = sql.zoekProfiel(this.gebruikersnaam,this.geboortejaar);
        if(alBestaand) throw new IllegalArgumentException("Het account bestaat al!");
    }

    public int getGeboortejaar() {
        return this.geboortejaar;
    }

    public String getGebruikersnaam() {
        return this.gebruikersnaam;
    }

    public int getAantalKansen() {
        return this.aantalKansen;
    }

    @Override
    public String toString(){
        return String.format("Actieve speler met gebruikersnaam \"%s\" en geboortedatum %d heeft %d speelkansen.", this.gebruikersnaam, this.geboortejaar, this.aantalKansen);
    }
}
