package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        getStyleClass().add("bg-image");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col1, col2);

        registerButton = new Button(rb.getString("register"));
        registerButton.setMaxWidth(Double.MAX_VALUE);
        add(registerButton, 0,5);
        GridPane.setColumnSpan(registerButton, 2);
        registerButton.setOnAction(this::registerButtonOnAction);

        cancelButton = new Button(rb.getString("back"));
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        add(cancelButton, 0,6);
        GridPane.setColumnSpan(cancelButton, 2);
        cancelButton.setOnAction(this::cancelButtonOnAction);

        usernameLabel = new Label(rb.getString("fillInUsername"));
        usernameLabel.setMaxWidth(Double.MAX_VALUE);
        usernameLabel.getStyleClass().add("lblText");
        add(usernameLabel, 0, 2);

        birthyearLabel = new Label(rb.getString("fillInBirthYear"));
        birthyearLabel.setMaxWidth(Double.MAX_VALUE);
        birthyearLabel.getStyleClass().add("lblText");
        add(birthyearLabel, 0, 3);

        usernameTextField = new TextField();
        usernameTextField.setMaxWidth(Double.MAX_VALUE);
        add(usernameTextField, 1, 2);
        usernameTextField.setPromptText(rb.getString("username"));

        birthyearTextField = new TextField();
        birthyearTextField.setMaxWidth(Double.MAX_VALUE);
        add(birthyearTextField, 1, 3);
        birthyearTextField.setPromptText(rb.getString("birthyear"));

        messageLabel = new Label();
        messageLabel.setMaxWidth(Double.MAX_VALUE);
        messageLabel.getStyleClass().add("lblLogin");
        add(messageLabel, 0, 4);
        GridPane.setColumnSpan(messageLabel, 2);
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
        String gebruikersnaam = usernameTextField.getText();
        int geboortejaar = Integer.parseInt(birthyearTextField.getText());
        boolean valid = true;

        if(valid) {
            if (usernameTextField.getText().length() < 5 || usernameTextField.getText().length() > 45)
                messageLabel.setText(rb.getString("minLengthUsername"));
            else if (year - Integer.parseInt(birthyearTextField.getText()) < 6 || year - Integer.parseInt(birthyearTextField.getText()) > 120)
                messageLabel.setText(rb.getString("minAge"));
            else {
                try {
                    dc.registreerSpeler(gebruikersnaam, geboortejaar);
                } catch (Exception e) {
                    valid = false;
                    messageLabel.setText(rb.getString("Exception"));
                    System.out.println(e);
                }
                if(valid) continueRegister();
            }
        }

    }

    private void continueRegister() {
        try {
            PlayerInformationController PlayerInformation = new PlayerInformationController(dc);
            Scene scene = new Scene(PlayerInformation, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        try {
            AddPlayersOptionController AddPlayer = new AddPlayersOptionController(dc);
            Scene scene = new Scene(AddPlayer, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


}
