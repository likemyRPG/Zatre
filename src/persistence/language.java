package persistence;
import java.util.*;
import java.util.ResourceBundle;

public class language {
    ResourceBundle rb = taal();
    Scanner myScanner = new Scanner(System.in);

    String gekozenTaal;

    public void keuze(){
        System.out.print("Please pick your preferred language! (fr, nl, en): ");
        gekozenTaal = myScanner.next();
    }

    public ResourceBundle taal(){
        Locale.setDefault(new Locale(gekozenTaal));
        ResourceBundle rb = ResourceBundle.getBundle("java/cfg/language");
        return rb;
    }


}
