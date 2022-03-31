package persistence;

import java.sql.*;
import persistence.SQLCommands;

public class MyJDBC {
    // Url van de database
    private static final String url = "jdbc:mysql://ID372720_g74.db.webhosting.be/ID372720_g74";
    // Gebruiker van de database
    private static final String user = "ID372720_g74";
    // Wachtwoord van de gebruiker
    private static final String password = "pHW3of4xcs5aXk4Yp6kU";
    // SQL-commando voor het zoeken of een speler account bestaat
    public static boolean zoekProfiel(String gebruikersnaam, int geboortedatum){
        // Gebruik maken van Try with Resources om er voor te zorgen dat de connectie altijd gesloten wordt
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();)
        {
            // Prepare statement van het SQL commando
            PreparedStatement pstmt = connection.prepareStatement(SQLCommands.zoekProfiel);
            // Vervang het ? in het sql commando door de variabele
            pstmt.setString(1, gebruikersnaam);
            pstmt.setInt(2, geboortedatum);
            // Execute de query
            ResultSet resultSet = pstmt.executeQuery();
            // return false wanneer er geen records worden gevonden
            return resultSet.next();
            // Catch voor mogelijke fouten
        }catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // SQL-commando voor het aantal speelkansen van een bepaalde gebruiker te zoeken
    public static int getAantalKansenBestaandeSpeler(String gebruikersnaam, int geboortedatum){
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();)
        {
            PreparedStatement pstmt = connection.prepareStatement(SQLCommands.getAantalKansenBestaandeSpeler);

            pstmt.setString(1, gebruikersnaam);
            pstmt.setInt(2, geboortedatum);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // SQL-commando voor een nieuw account aan te maken
    public static void maakProfiel(String gebruikersnaam, int geboortedatum, int aantalKansen){
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();)
        {
            PreparedStatement pstmt = connection.prepareStatement(SQLCommands.maakProfiel);
            pstmt.setString(1, gebruikersnaam);
            pstmt.setInt(2, geboortedatum);
            pstmt.setInt(3, aantalKansen);

            boolean resultSet = pstmt.execute();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void verminderSpeelkansen(String gebruikersnaam, int geboortedatum, int kansen) {
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();)
        {
            PreparedStatement pstmt = connection.prepareStatement(SQLCommands.verminderSpeelkansen);
            pstmt.setString(2, gebruikersnaam);
            pstmt.setInt(3, geboortedatum);
            pstmt.setInt(1, kansen);

            boolean resultSet = pstmt.execute();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}