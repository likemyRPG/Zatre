package persistence;

public class SQLCommands {

    public static final String geefProfielen = "SELECT * FROM 'speler';";
    public static final String zoekProfiel = "SELECT * FROM speler WHERE gebruikersnaam = ? AND geboortejaar = ?;";
    public static final String maakProfiel = "INSERT INTO speler (gebruikersnaam, geboortejaar, aantalKansen) VALUES (?, ?, ?);";
    public static final String getAantalKansenBestaandeSpeler = "SELECT aantalKansen FROM speler WHERE gebruikersnaam = ? AND geboortejaar = ?;";

}