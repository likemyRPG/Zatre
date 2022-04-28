package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static persistence.language.rb;

public class AddPlayersOptionController extends Pane {

    private DomeinController dc;


    Button btnReturn, btnLogin, btnRegister;

    Label lblKansen, lblPlayersNaam, lblPlayersKansen, lblPlayers;

    private int aantalSpelers;

    public AddPlayersOptionController(DomeinController dc){
        try
        {
            this.dc = dc;
            buildGUI();
            aantalSpelers = dc.geefAantalSpelers();
            buttonDisabled();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void buttonDisabled() {
        if(aantalSpelers == 4){
            btnLogin.setDisable(true);
            btnRegister.setDisable(false);
        }
    }

    private void buildGUI() {
        setPadding(new Insets(10));
        getStyleClass().add("bg-style");

        btnLogin = new Button(rb.getString("login"));
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        btnLogin.setOnAction(this::onClickLogin);
        btnLogin.getStyleClass().add("buttons");

        btnLogin.setFont(Font.font ("Berlin Sans FB", 24));
        btnLogin.setLayoutX(173.0);
        btnLogin.setLayoutY(94.0);

        //----------------------------//
        btnRegister = new Button(rb.getString("register"));
        btnRegister.setMaxWidth(Double.MAX_VALUE);
        btnRegister.setOnAction(this::onClickRegister);

        btnRegister.setFont(Font.font ("Berlin Sans FB", 24));
        btnRegister.setLayoutX(173.0);
        btnRegister.setLayoutY(94.0);

        //----------------------------//
        btnReturn = new Button("←");
        btnReturn.setMaxWidth(Double.MAX_VALUE);
        btnReturn.setOnAction(this::onClickReturn);

        btnReturn.setFont(Font.font ("Berlin Sans FB", 24));
        btnReturn.setLayoutX(173.0);
        btnReturn.setLayoutY(94.0);

        btnLogin.setLayoutX(50);
        btnLogin.setLayoutY(100);
        btnLogin.setMinWidth(500);

        btnRegister.setLayoutX(50);
        btnRegister.setLayoutY(150);
        btnRegister.setMinWidth(500);

        btnReturn.setLayoutY(10);
        btnReturn.setLayoutX(10);

        //Region Labels

        lblPlayers = new Label(rb.getString("players"));
        lblPlayers.setLayoutX(50);
        lblPlayers.setLayoutY(230);
        lblPlayers.setUnderline(true);
        lblPlayers.getStyleClass().add("lblText");

        lblKansen = new Label(rb.getString("chances"));
        lblKansen.setLayoutX(300);
        lblKansen.setLayoutY(230);
        lblKansen.setUnderline(true);
        lblKansen.getStyleClass().add("lblText");

        lblPlayersNaam = new Label(dc.geefSpelersNaam());

        lblPlayersNaam.setLayoutX(50);
        lblPlayersNaam.setLayoutY(250);
        lblPlayersNaam.getStyleClass().add("lblText");

        lblPlayersKansen = new Label(dc.geefSpelerKansen());

        lblPlayersKansen.setLayoutX(300);
        lblPlayersKansen.setLayoutY(250);
        lblPlayersKansen.getStyleClass().add("lblText");


        //endRegion

        this.getChildren().addAll(btnRegister, btnLogin, btnReturn, lblKansen, lblPlayersNaam, lblPlayersKansen, lblPlayers);
    }

    private void onClickRegister(ActionEvent event) {
        try {
            RegisterController register = new RegisterController(dc); // <1>
            Scene scene = new Scene(register, 600, 400);
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
        try {
            LoginController login = new LoginController(dc); // <1>
            Scene scene = new Scene(login, 600, 400);
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

    private void onClickReturn(ActionEvent event) {
        try {
            StartMenuController StartMenu = new StartMenuController(dc); // <1>
            Scene scene = new Scene(StartMenu, 600, 400);
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
