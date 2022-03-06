package App;

import domein.DomeinController;
import persistence.MyJDBC;
import java.util.*;

public class ZatreApp {

    private DomeinController dc;
    private MyJDBC db;
    Scanner myScanner = new Scanner(System.in);

    public ZatreApp(DomeinController dc) {
        this.dc = dc;
    }

    public void start(){
        db.maakConnectie();
        verwelkoming();
        inlogKeuze();
    }

    private void verwelkoming(){
        System.out.println("Welkom bij Zatre!");
        System.out.println("------------------");
    }

    private int inlogKeuze(){
        int keuze;

        do{
            System.out.println("Maak een keuze om verder te gaan:");
            System.out.println("~1~ Registreren");
            System.out.println("~2~ Inloggen");
            keuze = myScanner.nextInt();
        }while(keuze > 2 || keuze < 1);

        return keuze;
    }
}
