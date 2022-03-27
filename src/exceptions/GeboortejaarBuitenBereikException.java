package exceptions;

import persistence.language;


import java.util.ResourceBundle;

public class GeboortejaarBuitenBereikException extends Exception {



    public GeboortejaarBuitenBereikException() {

        super("Dit ligt buiten het bereik!");

    }

    public GeboortejaarBuitenBereikException(String s) {
        super(s);

    }

    public GeboortejaarBuitenBereikException(Throwable cause) {
        super(cause);

    }

    public GeboortejaarBuitenBereikException(String message, Throwable cause) {
        super(message, cause);

    }

}
