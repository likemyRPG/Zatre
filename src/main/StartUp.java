package main;
import domein.DomeinController;
import App.ZatreApp;

public class StartUp {
    public static void main(String[] args){
        DomeinController DC = new DomeinController();
        ZatreApp app = new ZatreApp(DC);
        app.start();
    }


}
