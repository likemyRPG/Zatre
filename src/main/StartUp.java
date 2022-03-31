package main;
import domein.DomeinController;
import exceptions.OutOfRangeException;
import ui.ZatreApp;

public class StartUp {
    public static void main(String[] args) throws OutOfRangeException {
        DomeinController DC = new DomeinController();
        ZatreApp app = new ZatreApp(DC);
        app.start();
    }


}
