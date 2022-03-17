package ui;
import domein.DomeinController;
import java.util.*;
import persistence.language;

public class ZatreApp {

    private DomeinController dc;
    Scanner myScanner = new Scanner(System.in);
    language ln = new language();
    ResourceBundle rb;
    int jaar = Calendar.getInstance().get(Calendar.YEAR);
    public static final int MIN_LEEFTIJD = 6;

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
        System.out.println(dc.geefSpelers());
    }

    //methode voor registreren OF selecteren van speler
    private void entryPoint(int keuze) {
        String gebruikersnaam;
        int geboortejaar;

        do{
            System.out.println(rb.getString("fillInUsername"));
            gebruikersnaam = myScanner.next();
        }while(gebruikersnaam.length() < 5);
        do{
            System.out.println(rb.getString("fillInBirthYear"));
            geboortejaar = myScanner.nextInt();
        }while(geboortejaar < 1900 || geboortejaar > jaar-MIN_LEEFTIJD);
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