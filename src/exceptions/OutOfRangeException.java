package exceptions;

import persistence.language;


import java.util.ResourceBundle;

public class OutOfRangeException extends Exception {
    public OutOfRangeException() {
        super("Dit ligt buiten het bereik!");
    }

    public OutOfRangeException(String s) {
        super(s);
    }

    public OutOfRangeException(Throwable cause) {
        super(cause);
    }

    public OutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

}
