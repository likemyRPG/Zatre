package persistence;
import java.util.*;

public class language {
    static public ResourceBundle rb;
    Scanner myScanner = new Scanner(System.in);
    String gekozenTaal;
    String[] talen = {"en", "nl", "fr"};

    public void keuze(){
        do{
            System.out.print("Please pick your preferred language! (fr, nl, en): ");
            gekozenTaal = myScanner.next();
        }while(!Arrays.toString(talen).contains(gekozenTaal.toLowerCase()) || gekozenTaal.length() != 2);
    }



    public ResourceBundle taal(){
        if (rb == null)
        {
            Locale.setDefault(new Locale(gekozenTaal));
            rb = ResourceBundle.getBundle("config/language/language");
        }
        return rb;
    }
    public ResourceBundle taal2(String taal){
        if (rb == null)
        {
            Locale.setDefault(new Locale(taal));
            rb = ResourceBundle.getBundle("config/language/language");
        }
        return rb;
    }
}