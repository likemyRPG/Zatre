package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class MyJDBC {
    public static void maakConnectie(){

        try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://ID372720_g74.db.webhosting.be/ID372720_g74", "ID372720_g74", "pHW3of4xcs5aXk4Yp6kU");

            Statement statement = connection.createStatement();

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

}
