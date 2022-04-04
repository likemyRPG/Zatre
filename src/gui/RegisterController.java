package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.util.Calendar;

import static persistence.language.rb;

public class RegisterController extends Pane {

    private DomeinController dc;
    private Label usernameLabel;
    private Label birthyearLabel;
    private TextField usernameTextField;
    private TextField birthyearTextField;
    private Label messageLabel;
    private Button registerButton;
    private Button cancelButton;

    public RegisterController(DomeinController dc) {
        try{
            this.dc = dc;
            buildGui();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void buildGui() {
        usernameLabel = new Label(rb.getString("username"));
        birthyearLabel = new Label(rb.getString("birthyear"));
        usernameTextField = new TextField(rb.getString("username"));
        birthyearTextField = new TextField(rb.getString("birthyear"));
        registerButton = new Button(rb.getString("register"));
        cancelButton = new Button(rb.getString("cancel"));
    }

    public void registerButtonOnAction(ActionEvent event) {
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (usernameTextField.getText().isBlank() == false && birthyearTextField.getText().isBlank() == false)
                checkRegister();
        else {
            throw new IllegalArgumentException(rb.getString("Username&Birthday"));
        }
    }

    public void checkRegister() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        boolean valid = true;
        if(valid) {
            if (usernameTextField.getText().length() < 5)
                messageLabel.setText(rb.getString("minLengthUsername"));
            if (year - Integer.parseInt(birthyearTextField.getText()) < 6)
                messageLabel.setText(rb.getString("minAge"));
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
