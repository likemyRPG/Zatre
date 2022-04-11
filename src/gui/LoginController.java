package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.util.ResourceBundle;

import static persistence.language.rb;

public class LoginController extends GridPane {
    private DomeinController dc;
    private Button btnCancel;
    private Label UsernameLabel;
    private Label birthYearLabel;
    private Label lblLoginMessage;
    private TextField usernameTextField;
    private TextField birthYearTextField;
    private ComboBox birthYear;
    private Button btnLogin;

    public LoginController(DomeinController dc) {
        try{
            this.dc = dc;
            buildGUI();
        }catch( Exception e){
            System.out.println(e);
        }
    }

    private void buildGUI() {
        setVgap(20);
        setHgap(10);
        setPadding(new Insets(10));
        getStyleClass().add("bg-style");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col1, col2);

        btnLogin = new Button(rb.getString("login"));
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        add(btnLogin, 0,5);
        GridPane.setColumnSpan(btnLogin, 2);
        btnLogin.setOnAction(this::onClickLogin);

        UsernameLabel = new Label(rb.getString("fillInUsername"));
        UsernameLabel.setMaxWidth(Double.MAX_VALUE);
        UsernameLabel.getStyleClass().add("lblText");
        add(UsernameLabel, 0, 2);

        birthYearLabel = new Label(rb.getString("fillInBirthYear"));
        birthYearLabel.setMaxWidth(Double.MAX_VALUE);
        birthYearLabel.getStyleClass().add("lblText");
        add(birthYearLabel, 0, 3);

        usernameTextField = new TextField();
        usernameTextField.setMaxWidth(Double.MAX_VALUE);
        add(usernameTextField, 1, 2);
        usernameTextField.setPromptText(rb.getString("username"));

        birthYearTextField = new TextField();
        birthYearTextField.setMaxWidth(Double.MAX_VALUE);
        add(birthYearTextField, 1, 3);
        birthYearTextField.setPromptText(rb.getString("birthyear"));

        lblLoginMessage = new Label();
        lblLoginMessage.setMaxWidth(Double.MAX_VALUE);
        lblLoginMessage.getStyleClass().add("lblLogin");
        add(lblLoginMessage, 0, 4);
        GridPane.setColumnSpan(lblLoginMessage, 2);

        btnCancel = new Button(rb.getString("back"));
        btnCancel.setMaxWidth(Double.MAX_VALUE);
        add(btnCancel, 0,6);
        GridPane.setColumnSpan(btnCancel, 2);
        btnCancel.setOnAction(this::onClickCancel);
    }

    private void onClickCancel(ActionEvent event) {
        try {
            AddPlayersOptionController AddPlayer = new AddPlayersOptionController(dc);
            Scene scene = new Scene(AddPlayer, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private void onClickLogin(ActionEvent event) {
        if(usernameTextField.getText().isBlank() == false && birthYearTextField.getText().isBlank() == false) checkLogin();
        else lblLoginMessage.setText(rb.getString("Username&Birthday"));
    }

    public void checkLogin(){
        String gebruikersnaam = usernameTextField.getText();
        int geboortejaar = Integer.parseInt(birthYearTextField.getText());
        boolean succes = true;
        boolean alToegevoegd = dc.alToegevoegd(gebruikersnaam, geboortejaar);
        if(alToegevoegd) lblLoginMessage.setText("Speler is al toegevoegd!");
        else{
            try {
                dc.selecteerSpeler(gebruikersnaam, geboortejaar);
            }catch (Exception e){
                lblLoginMessage.setText(rb.getString("Exception"));
                succes = false;
            }
            if(succes) continueLogin();
        }

    }

    private void continueLogin() {
        try {
            PlayerInformationController PlayerInformation = new PlayerInformationController(dc);
            Scene scene = new Scene(PlayerInformation, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
