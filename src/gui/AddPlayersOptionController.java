package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static persistence.language.rb;

public class AddPlayersOptionController extends GridPane {

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
            btnDelete1.setVisible(false);
            btnDelete2.setVisible(false);
            btnDelete3.setVisible(false);
            btnDelete4.setVisible(false);
        }
        else if(dc.geefAantalSpelers() == 1){
            btnDelete1.setDisable(false);
            btnDelete2.setDisable(true);
            btnDelete3.setDisable(true);
            btnDelete4.setDisable(true);
            btnDelete1.setVisible(true);
            btnDelete2.setVisible(false);
            btnDelete3.setVisible(false);
            btnDelete4.setVisible(false);
        }
        else if(dc.geefAantalSpelers() == 2){
            btnDelete1.setDisable(false);
            btnDelete2.setDisable(false);
            btnDelete3.setDisable(true);
            btnDelete4.setDisable(true);
            btnDelete1.setVisible(true);
            btnDelete2.setVisible(true);
            btnDelete3.setVisible(false);
            btnDelete4.setVisible(false);
        }
        else if(dc.geefAantalSpelers() == 3){
            btnDelete1.setDisable(false);
            btnDelete2.setDisable(false);
            btnDelete3.setDisable(false);
            btnDelete4.setDisable(true);
            btnDelete1.setVisible(true);
            btnDelete2.setVisible(true);
            btnDelete3.setVisible(true);
            btnDelete4.setVisible(false);
        }
        else if(dc.geefAantalSpelers() == 4){
            btnDelete1.setDisable(false);
            btnDelete2.setDisable(false);
            btnDelete3.setDisable(false);
            btnDelete4.setDisable(false);
            btnDelete1.setVisible(true);
            btnDelete2.setVisible(true);
            btnDelete3.setVisible(true);
            btnDelete4.setVisible(true);
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
        ColumnConstraints col5 = new ColumnConstraints();
        ColumnConstraints col6 = new ColumnConstraints();
        ColumnConstraints col7 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);

        col1.setPercentWidth(2);
        col2.setPercentWidth(5);
        col3.setPercentWidth(3);
        col4.setPercentWidth(25);
        col5.setPercentWidth(25);
        col6.setPercentWidth(2);
        col7.setPercentWidth(37);

        getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);


        btnLogin = new Button(rb.getString("login"));
        btnLogin.setMaxWidth(240);
        btnLogin.setOnAction(this::onClickLogin);
        btnLogin.getStyleClass().add("buttons");
        add(btnLogin, 3, 3);

        //----------------------------//
        btnRegister = new Button(rb.getString("register"));
        btnRegister.setMaxWidth(240);
        btnRegister.setMaxHeight(Double.MAX_VALUE);
        btnRegister.setOnAction(this::onClickRegister);
        add(btnRegister, 4, 3);

        //Add 4 buttons to the pane to delete the player from the list of players
        btnDelete1 = new Button("-");
        btnDelete1.setMaxWidth(Double.MAX_VALUE);
        btnDelete1.setMaxHeight(20);
        btnDelete1.setOnAction(this::onClickDelete1);
        btnDelete1.setPadding(new Insets(0));
        btnDelete1.setLayoutX(360);
        btnDelete1.setLayoutY(250);
        add(btnDelete1, 5, 7);

        btnDelete2 = new Button("-");
        btnDelete2.setMaxWidth(Double.MAX_VALUE);
        btnDelete2.setMaxHeight(20);
        btnDelete2.setOnAction(this::onClickDelete2);
        btnDelete2.setPadding(new Insets(0));
        btnDelete2.setLayoutX(360);
        btnDelete2.setLayoutY(275);
        add(btnDelete2, 5, 8);

        btnDelete3 = new Button("-");
        btnDelete3.setMaxWidth(Double.MAX_VALUE);
        btnDelete3.setMaxHeight(20);
        btnDelete3.setOnAction(this::onClickDelete3);
        btnDelete3.setPadding(new Insets(0));
        btnDelete3.setLayoutX(360);
        btnDelete3.setLayoutY(300);
        add(btnDelete3, 5, 9);

        btnDelete4 = new Button("-");
        btnDelete4.setMaxWidth(Double.MAX_VALUE);
        btnDelete4.setMaxHeight(20);
        btnDelete4.setOnAction(this::onClickDelete4);
        btnDelete4.setPadding(new Insets(0));
        btnDelete4.setLayoutX(360);
        btnDelete4.setLayoutY(325);
        add(btnDelete4, 5, 10);


        VBox vbox = new VBox(btnDelete1, btnDelete2, btnDelete3, btnDelete4);
        vbox.setSpacing(15);
        add(vbox, 5, 7);
        GridPane.setRowSpan(vbox, 4);


        //----------------------------//
        btnReturn = new Button("←");
        btnReturn.setMaxWidth(Double.MAX_VALUE);
        btnReturn.setMaxHeight(Double.MAX_VALUE);
        btnReturn.setOnAction(this::onClickReturn);

        btnReturn.setLayoutX(173.0);
        btnReturn.setLayoutY(94.0);
        add(btnReturn, 1, 1);

        btnLogin.setLayoutX(50);
        btnLogin.setLayoutY(100);

        btnRegister.setLayoutX(50);
        btnRegister.setLayoutY(150);

        btnReturn.setLayoutY(10);
        btnReturn.setLayoutX(10);

        //Region Labels

        lblPlayers = new Label(rb.getString("players"));
        lblPlayers.setLayoutX(50);
        lblPlayers.setLayoutY(230);
        lblPlayers.setUnderline(true);
        lblPlayers.getStyleClass().add("lblText");
        add(lblPlayers, 3, 6);


        lblKansen = new Label(rb.getString("chances"));
        lblKansen.setLayoutX(300);
        lblKansen.setLayoutY(230);
        lblKansen.setUnderline(true);
        lblKansen.getStyleClass().add("lblText");
        add(lblKansen, 4, 6);
    
        lblPlayersNaam = new Label(dc.geefSpelersNaam());
        lblPlayersNaam.setLayoutX(50);
        lblPlayersNaam.setLayoutY(250);
        lblPlayersNaam.getStyleClass().add("lblText");
        lblPlayersNaam.setPadding(new Insets(0));
        lblPlayersNaam.setContentDisplay(ContentDisplay.TOP);
        lblPlayersNaam.setTextAlignment(TextAlignment.LEFT);
        lblPlayersNaam.setStyle("-fx-alignment: top-left");
        add(lblPlayersNaam, 3, 7);

        lblPlayersKansen = new Label(dc.geefSpelerKansen());
        lblPlayersKansen.getStyleClass().add("lblText");
        add(lblPlayersKansen, 4, 7);



        //endRegion
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
