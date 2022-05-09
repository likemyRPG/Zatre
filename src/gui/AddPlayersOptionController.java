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


    Button btnReturn, btnLogin, btnRegister, btnDelete1, btnDelete2, btnDelete3, btnDelete4;

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
            btnRegister.setDisable(true);
        }

        //Disable buttons dependig on the amount of players
        if(dc.geefAantalSpelers() == 0){
            btnDelete1.setDisable(true);
            btnDelete2.setDisable(true);
            btnDelete3.setDisable(true);
            btnDelete4.setDisable(true);
        }
        else if(dc.geefAantalSpelers() == 1){
            btnDelete1.setDisable(false);
            btnDelete2.setDisable(true);
            btnDelete3.setDisable(true);
            btnDelete4.setDisable(true);
        }
        else if(dc.geefAantalSpelers() == 2){
            btnDelete1.setDisable(false);
            btnDelete2.setDisable(false);
            btnDelete3.setDisable(true);
            btnDelete4.setDisable(true);
        }
        else if(dc.geefAantalSpelers() == 3){
            btnDelete1.setDisable(false);
            btnDelete2.setDisable(false);
            btnDelete3.setDisable(false);
            btnDelete4.setDisable(true);
        }
        else if(dc.geefAantalSpelers() == 4){
            btnDelete1.setDisable(false);
            btnDelete2.setDisable(false);
            btnDelete3.setDisable(false);
            btnDelete4.setDisable(false);
        }
    }

    private void buildGUI() {
        setPadding(new Insets(10));
        getStyleClass().add("bg-image");

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

        //Add 4 buttons to the pane to delete the player from the list of players
        btnDelete1 = new Button("-");
        btnDelete1.setMaxWidth(Double.MAX_VALUE);
        btnDelete1.setMaxHeight(20);
        btnDelete1.setOnAction(this::onClickDelete1);
        btnDelete1.setPadding(new Insets(0));
        btnDelete1.setFont(Font.font ("Berlin Sans FB", 8));
        btnDelete1.setLayoutX(360);
        btnDelete1.setLayoutY(250);

        btnDelete2 = new Button("-");
        btnDelete2.setMaxWidth(Double.MAX_VALUE);
        btnDelete2.setMaxHeight(20);
        btnDelete2.setOnAction(this::onClickDelete2);
        btnDelete2.setPadding(new Insets(0));
        btnDelete2.setFont(Font.font ("Berlin Sans FB", 8));
        btnDelete2.setLayoutX(360);
        btnDelete2.setLayoutY(275);

        btnDelete3 = new Button("-");
        btnDelete3.setMaxWidth(Double.MAX_VALUE);
        btnDelete3.setMaxHeight(20);
        btnDelete3.setOnAction(this::onClickDelete3);
        btnDelete3.setPadding(new Insets(0));
        btnDelete3.setFont(Font.font ("Berlin Sans FB", 8));
        btnDelete3.setLayoutX(360);
        btnDelete3.setLayoutY(300);

        btnDelete4 = new Button("-");
        btnDelete4.setMaxWidth(Double.MAX_VALUE);
        btnDelete4.setMaxHeight(20);
        btnDelete4.setOnAction(this::onClickDelete4);
        btnDelete4.setPadding(new Insets(0));
        btnDelete4.setFont(Font.font ("Berlin Sans FB", 8));
        btnDelete4.setLayoutX(360);
        btnDelete4.setLayoutY(325);


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

        this.getChildren().addAll(btnDelete1, btnDelete2, btnDelete3, btnDelete4, btnRegister, btnLogin, btnReturn, lblKansen, lblPlayersNaam, lblPlayersKansen, lblPlayers);
    }

    private void onClickDelete2(ActionEvent event) {
        dc.verwijderSpeler(1);
        lblPlayersKansen.setText(dc.geefSpelerKansen());
        lblPlayersNaam.setText(dc.geefSpelersNaam());
        buttonDisabled();
    }

    private void onClickDelete3(ActionEvent event) {
        dc.verwijderSpeler(2);
        lblPlayersKansen.setText(dc.geefSpelerKansen());
        lblPlayersNaam.setText(dc.geefSpelersNaam());
        buttonDisabled();
    }

    private void onClickDelete4(ActionEvent event) {
        dc.verwijderSpeler(3);
        lblPlayersKansen.setText(dc.geefSpelerKansen());
        lblPlayersNaam.setText(dc.geefSpelersNaam());
        buttonDisabled();
    }

    private void onClickDelete1(ActionEvent event) {
        dc.verwijderSpeler(0);
        lblPlayersKansen.setText(dc.geefSpelerKansen());
        lblPlayersNaam.setText(dc.geefSpelersNaam());
        buttonDisabled();
    }

    private void onClickRegister(ActionEvent event) {
        try {
            RegisterController register = new RegisterController(dc); // <1>
            Scene scene = new Scene(register, 900, 645);
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
            Scene scene = new Scene(login, 900, 645);
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
            Scene scene = new Scene(StartMenu, 900, 645);
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
