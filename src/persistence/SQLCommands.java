package persistence;

public class SQLCommands {

    // Alle SQL-commands
    public static final String zoekProfiel = "SELECT * FROM speler WHERE gebruikersnaam = ? AND geboortejaar = ?;";
    public static final String maakProfiel = "INSERT INTO speler (gebruikersnaam, geboortejaar, aantalKansen) VALUES (?, ?, ?);";
    public static final String getAantalKansenBestaandeSpeler = "SELECT aantalKansen FROM speler WHERE gebruikersnaam = ? AND geboortejaar = ?;";
    public static final String verminderSpeelkansen = "UPDATE speler SET aantalKansen=? WHERE gebruikersnaam = ? AND geboortejaar = ?;";
}