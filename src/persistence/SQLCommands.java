package persistence;

public class SQLCommands {

    public static final String zoekProfiel = "Select * FROM 'speler' WHERE 'gebruikersnaam' = ? AND 'geboortejaar = ?";
    public static final String maakProfiel = "Insert into speler (gebruikersnaam, geboortejaar, aantalKansen) VALUES (?, ?, ?)";
}
