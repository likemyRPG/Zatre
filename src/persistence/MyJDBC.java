package persistence;

import java.sql.*;
import persistence.SQLCommands;

public class MyJDBC {
    private static final String url = "jdbc:mysql://ID372720_g74.db.webhosting.be/ID372720_g74";
    private static final String user = "ID372720_g74";
    private static final String password = "pHW3of4xcs5aXk4Yp6kU";
    public static void geefProfielen(){
        // Gebruik maken van Try with Resources om er voor te zorgen dat de connectie altijd gesloten wordt
        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();)
        {
            ResultSet resultSet = statement.executeQuery(SQLCommands.geefProfielen);

            while (resultSet.next()){
                System.out.println(resultSet.getString("gebruikersnaam"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean zoekProfiel(String gebruikersnaam, int geboortedatum){
        try{
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = connection.prepareStatement(SQLCommands.zoekProfiel);
            pstmt.setString(1, gebruikersnaam);
            pstmt.setInt(2, geboortedatum);

            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next() == false) return false;
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public static int getAantalKansenBestaandeSpeler(String gebruikersnaam, int geboortedatum){
        try{
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = connection.prepareStatement(SQLCommands.getAantalKansenBestaandeSpeler);
            pstmt.setString(1, gebruikersnaam);
            pstmt.setInt(2, geboortedatum);

            ResultSet resultSet = pstmt.getResultSet();

            return resultSet.getInt(1);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void maakProfiel(String gebruikersnaam, int geboortedatum, int aantalKansen){
        try{
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = connection.prepareStatement(SQLCommands.maakProfiel);
            pstmt.setString(1, gebruikersnaam);
            pstmt.setInt(2, geboortedatum);
            pstmt.setInt(3, aantalKansen);

            boolean resultSet = pstmt.execute();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}