package ui;
import domein.DomeinController;
import java.util.*;
import persistence.language;

public class ZatreApp {

    //region Variabelen
    private DomeinController dc;
    Scanner myScanner = new Scanner(System.in);
    language ln = new language();
    ResourceBundle rb;
    int jaar = Calendar.getInstance().get(Calendar.YEAR);
    int aantalActieveSpelers = 0;
    public static final int MIN_LEEFTIJD = 6;
    //endregion

    public ZatreApp(DomeinController dc) {
        this.dc = dc;
    }

    public void start(){
        ln.keuze();
        rb = ln.taal();
        verwelkoming();
        inleiding();
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
        else {
            dc.selecteerSpeler(gebruikersnaam, geboortejaar);
            aantalActieveSpelers++;
        }
        System.out.println(dc.geefOverzicht());
    }

    private void verwelkoming(){
        System.out.println(rb.getString("welcome"));
        System.out.println("------------------");
    }

    private void toonSpelers(){
        System.out.printf("%d " + rb.getString("active") + " %s%n", aantalActieveSpelers,aantalActieveSpelers<1 ? rb.getString("players"): rb.getString("player"));
        System.out.printf("------------------%n");
        System.out.printf("%s\t\t%4s%n", rb.getString("name"), rb.getString("chances"));
        System.out.print(dc.geefSpelers());
    }

    private int keuzeMenu(){
        int keuze;
        do{
            System.out.println();
            System.out.println(rb.getString("choice"));
            System.out.println("~1~ " + rb.getString("addUser"));
            System.out.println("~2~ " + rb.getString("startGame"));
            System.out.println("~3~ " + rb.getString("quit"));
            keuze = myScanner.nextInt();
            return keuze;
        }while(keuze > 3 || keuze < 1);
    }

    private int inlogKeuze(){
        int keuze;
        do{
            System.out.println();
            System.out.println(rb.getString("choice"));
            System.out.println("~1~ " + rb.getString("register"));
            System.out.println("~2~ " + rb.getString("login"));
            System.out.println("~3~ " + rb.getString("back") );
            keuze = myScanner.nextInt();
        }while(keuze > 3 || keuze < 1);

        return keuze;
    }

    private void inleiding(){
        int keuze = keuzeMenu();
        do{
            do{
                if(keuze == 1) {
                    if(aantalActieveSpelers < 4)
                    {
                        int inlogKeuze = inlogKeuze();
                        if (inlogKeuze == 1) entryPoint(inlogKeuze);
                        else if(inlogKeuze == 2) entryPoint(inlogKeuze);
                        else if(inlogKeuze == 3) break;
                        toonSpelers();
                    }
                    else throw new IllegalArgumentException(rb.getString("maxplayers"));

                }
                else if(keuze == 2){
                    dc.startSpel();
                    toonSpelers();
                }
            }while(keuze < 1 || keuze > 2);
            keuze = keuzeMenu();
        }while(keuze != 3);
    }
}