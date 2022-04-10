package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.util.Calendar;

import static persistence.language.rb;

public class RegisterController extends GridPane {

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
            buildGUI();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void buildGUI() {
        setVgap(20);
        setHgap(10);
        setPadding(new Insets(10));
        getStyleClass().add("bg-style");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col1, col2);


        registerButton = new Button(rb.getString("register"));
        registerButton.setMaxWidth(Double.MAX_VALUE);
        add(registerButton, 1,5);
        registerButton.setOnAction(this::registerButtonOnAction);

        cancelButton = new Button(rb.getString("cancel"));
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        add(cancelButton, 1,6);
        cancelButton.setOnAction(this::cancelButtonOnAction);

        usernameTextField = new TextField();
        usernameTextField.setMaxWidth(Double.MAX_VALUE);
        add(usernameTextField, 1, 2);
        usernameTextField.setPromptText(rb.getString("fillInUsername"));

        birthyearTextField = new TextField();
        birthyearTextField.setMaxWidth(Double.MAX_VALUE);
        add(birthyearTextField, 1, 3);
        birthyearTextField.setPromptText(rb.getString("fillInBirthYear"));

        messageLabel = new Label();
        messageLabel.setMaxWidth(Double.MAX_VALUE);
        messageLabel.getStyleClass().add("lblLogin");
        add(messageLabel, 1, 4);

    }

    public void registerButtonOnAction(ActionEvent event) {
        int year = Calendar.getInstance().get(Calendar.YEAR);


        if (usernameTextField.getText().isBlank() == false && birthyearTextField.getText().isBlank() == false)
                checkRegister();
        else {
            messageLabel.setText(rb.getString("Username&Birthday"));
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
        try {
            AddPlayersOptionController AddPlayer = new AddPlayersOptionController(dc);
            Scene scene = new Scene(AddPlayer, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


}
