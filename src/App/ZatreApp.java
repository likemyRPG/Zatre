package App;

import domein.DomeinController;

public class ZatreApp {

    private DomeinController dc;

    public ZatreApp(DomeinController dc) {
        this.dc = dc;
    }

    public void start(){
        System.out.printf("Hello world");
    }
}
