package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Calendar;

import static persistence.language.rb;

public class LoginController extends GridPane {
    private DomeinController dc;
    private Button btnCancel;
    private Label UsernameLabel;
    private Label birthYearLabel;
    private Label lblLoginMessage;
    private TextField usernameTextField;
    private ComboBox birthyearComboBox;
    private Button btnLogin;

    public LoginController(DomeinController dc) {
        try{
            this.dc = dc;
            buildGUI();
            addBirthyearToComboBox();
        }catch( Exception e){
            System.out.println(e);
        }
    }

    private void buildGUI() {
        setVgap(20);
        setHgap(10);
        setPadding(new Insets(10));
        getStyleClass().add("bg-image");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        col1.setPercentWidth(15);
        col2.setPercentWidth(30);
        col3.setPercentWidth(20);
        col4.setPercentWidth(35);
        getColumnConstraints().addAll(col1, col2, col3, col4);

        btnLogin = new Button(rb.getString("login"));
        btnLogin.setMaxWidth(240);
        add(btnLogin, 1,11);
        GridPane.setColumnSpan(btnLogin, 2);
        btnLogin.setOnAction(this::onClickLogin);

        UsernameLabel = new Label(rb.getString("fillInUsername"));
        UsernameLabel.getStyleClass().add("lblText");
        add(UsernameLabel, 1, 4);
        UsernameLabel.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setColumnSpan(UsernameLabel, 2);

        birthYearLabel = new Label(rb.getString("fillInBirthYear"));
        birthYearLabel.getStyleClass().add("lblText");
        add(birthYearLabel, 1, 7);
        birthYearLabel.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setColumnSpan(birthYearLabel, 2);

        usernameTextField = new TextField();
        usernameTextField.setMaxWidth(Double.MAX_VALUE);
        add(usernameTextField, 1, 5);
        usernameTextField.setPromptText(rb.getString("username"));
        GridPane.setColumnSpan(usernameTextField, 2);

        birthyearComboBox = new ComboBox<>();
        birthyearComboBox.setMaxWidth(Double.MAX_VALUE);
        add(birthyearComboBox, 1, 8);
        GridPane.setColumnSpan(birthyearComboBox, 2);

        lblLoginMessage = new Label();
        lblLoginMessage.setMaxWidth(Double.MAX_VALUE);
        lblLoginMessage.getStyleClass().add("lblLogin");
        add(lblLoginMessage, 1, 9);
        GridPane.setColumnSpan(lblLoginMessage, 2);

        btnCancel = new Button(rb.getString("back"));
        btnCancel.setMaxWidth(240);
        add(btnCancel, 1,12);
        GridPane.setColumnSpan(btnCancel, 2);
        btnCancel.setOnAction(this::onClickCancel);
    }

    private void onClickCancel(ActionEvent event) {
        try {
            AddPlayersOptionController AddPlayer = new AddPlayersOptionController(dc);
            Scene scene = new Scene(AddPlayer, 900, 645);
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

    private void onClickLogin(ActionEvent event) {
        if(usernameTextField.getText().isBlank() == false && birthyearComboBox.getValue() != null) checkLogin();
        else lblLoginMessage.setText(rb.getString("Username&Birthday"));
    }

    private void addBirthyearToComboBox() {
        Calendar cal = Calendar.getInstance();
        for(int i = cal.get(Calendar.YEAR)-6; i >= 1900; i--) {
            birthyearComboBox.getItems().add(i);
        }
    }

    public void checkLogin(){
        String gebruikersnaam = usernameTextField.getText();
        int geboortejaar = (int) birthyearComboBox.getValue();
        boolean succes = true;
        boolean alToegevoegd = dc.alToegevoegd(gebruikersnaam, geboortejaar);
        if(alToegevoegd) lblLoginMessage.setText(rb.getString("playerAlreadyAdded"));
        else{
            try {
                dc.selecteerSpeler(gebruikersnaam, geboortejaar);
            }catch (StringIndexOutOfBoundsException e){
                lblLoginMessage.setText(rb.getString("playerDeletedNoLives"));
                succes=false;
            }
            catch (Exception e){
                lblLoginMessage.setText(rb.getString("Exception"));
                succes = false;
            }
            if(succes) continueLogin();
        }
    }

    private void continueLogin() {
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
}
