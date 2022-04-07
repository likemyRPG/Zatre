package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import persistence.language;

import java.util.ResourceBundle;

public class StartMenuController extends GridPane {

    private DomeinController dc;
    private Button btnQuit;
    private Button btnStartGame;
    private Button btnSelectPlayers;

    language ln = new language();
    ResourceBundle rb = ln.taal();

    private int aantalSpelers;

    public StartMenuController(DomeinController dc) {
        try
        {
            this.dc = dc;
            dc.selecteerSpeler("Larss",2003);
            dc.selecteerSpeler("Jochen",2003);
            buildGUI();
            aantalSpelers = dc.geefAantalSpelers();
            buttonDisabled();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void buttonDisabled() {
        if(aantalSpelers < 2) btnStartGame.setDisable(true);
        if(aantalSpelers == 4) btnSelectPlayers.setDisable(true);
    }

    private void buildGUI() {
        setVgap(20);
        setHgap(10);
        setPadding(new Insets(10));
          ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        getStyleClass().add("bg-style");

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col1, col2);

        btnSelectPlayers = new Button(rb.getString("players"));
        btnSelectPlayers.setMaxWidth(Double.MAX_VALUE);
        add(btnSelectPlayers, 1,3);
        btnSelectPlayers.setOnAction(this::onClickSelectPlayers);
        btnSelectPlayers.getStyleClass().add("buttons");

        btnSelectPlayers.setFont(Font.font ("Berlin Sans FB", 24));
        btnSelectPlayers.setLayoutX(173.0);
        btnSelectPlayers.setLayoutY(94.0);

        //----------------------------//
        btnStartGame = new Button(rb.getString("startGame"));
        btnStartGame.setMaxWidth(Double.MAX_VALUE);
        add(btnStartGame, 1,4);
        btnStartGame.setOnAction(this::onClickStartGame);

        btnStartGame.setFont(Font.font ("Berlin Sans FB", 24));
        btnStartGame.setLayoutX(173.0);
        btnStartGame.setLayoutY(94.0);

        //----------------------------//
        btnQuit = new Button(rb.getString("quit"));
        btnQuit.setMaxWidth(Double.MAX_VALUE);
        add(btnQuit, 1,5);
        btnQuit.setOnAction(this::onClickQuit);

        btnQuit.setFont(Font.font ("Berlin Sans FB", 24));
        btnQuit.setLayoutX(173.0);
        btnQuit.setLayoutY(94.0);
    }

    public void onClickSelectPlayers(ActionEvent event) {
        try
        {
            AddPlayersOptionController AddPlayer = new AddPlayersOptionController(dc); // <1>
            Scene scene = new Scene(AddPlayer, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void onClickStartGame(ActionEvent event) {
        try {
            GameStartScreen GameStart = new GameStartScreen(dc);
            Scene scene = new Scene(GameStart, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void onClickQuit(ActionEvent event) {
        try{
            StartScreenController StartScreen = new StartScreenController(dc); // <1>
            Scene scene = new Scene(StartScreen, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}