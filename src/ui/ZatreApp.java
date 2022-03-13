package ui;
import domein.DomeinController;
import java.util.*;
import persistence.language;

public class ZatreApp {

    private DomeinController dc;
    Scanner myScanner = new Scanner(System.in);
    language ln = new language();
    ResourceBundle rb;

    public ZatreApp(DomeinController dc) {
        this.dc = dc;
    }

    public void start(){
        ln.keuze();
        rb = ln.taal();
        dc.startSpel();
        verwelkoming();
        int keuze = inlogKeuze();
        while(keuze != 3) {
            entryPoint(keuze);
            keuze = inlogKeuze();
        }
    }

    //methode voor registreren OF selecteren van speler
    private void entryPoint(int keuze) {
        System.out.println(rb.getString("fillInUsername"));
        String gebruikersnaam = myScanner.next();
        System.out.println(rb.getString("fillInBirthYear"));
        int geboortejaar = myScanner.nextInt();
        if(keuze == 1) dc.registreerSpeler(gebruikersnaam, geboortejaar);
        else dc.selecteerSpeler(gebruikersnaam, geboortejaar);
        System.out.println(dc.geefOverzicht());
    }

    private void verwelkoming(){
        System.out.println("Welcome to Zatre!");
        System.out.println("------------------");
    }

    private int inlogKeuze(){
        int keuze;

        do{
            System.out.println(rb.getString("choice"));
            System.out.println("~1~ " + rb.getString("register"));
            System.out.println("~2~ " + rb.getString("login"));
            System.out.println("~3~ " + rb.getString("quit"));
            keuze = myScanner.nextInt();
        }while(keuze > 3 || keuze < 1);

        return keuze;
    }
}