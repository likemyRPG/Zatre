package main;
import domein.DomeinController;
import exceptions.GeboortejaarBuitenBereikException;
import ui.ZatreApp;

public class StartUp {
    public static void main(String[] args) throws GeboortejaarBuitenBereikException {
        DomeinController DC = new DomeinController();
        ZatreApp app = new ZatreApp(DC);
        app.start();
    }


}
