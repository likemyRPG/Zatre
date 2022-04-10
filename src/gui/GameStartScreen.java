package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static persistence.language.rb;

public class GameStartScreen extends Pane {
    private DomeinController dc;
    private Button btnOk;
    private Label lblPlayersKansen, lblPlayersNaam;

    public GameStartScreen(DomeinController dc){
        try {
            this.dc = dc;
            playerMan();
            buildGUI();
        }catch  (Exception e){
            System.out.println(e);
        }
    }

    private void playerMan() {
        dc.startSpel();
    }

    private void buildGUI() {
        setPadding(new Insets(10));
        getStyleClass().add("bg-style");

        btnOk = new Button("Ok");
        btnOk.setMaxWidth(Double.MAX_VALUE);
        btnOk.setMinWidth(200);
        btnOk.setOnAction(this::onClickButtonOk);

        btnOk.setLayoutY(230);
        btnOk.setLayoutX(200);

        lblPlayersNaam = new Label(dc.geefSpelersNaam());

        lblPlayersNaam.setLayoutX(50);
        lblPlayersNaam.setLayoutY(250);
        lblPlayersNaam.getStyleClass().add("lblText");

        lblPlayersKansen = new Label(dc.geefSpelerKansen());

        lblPlayersKansen.setLayoutX(300);
        lblPlayersKansen.setLayoutY(250);
        lblPlayersKansen.getStyleClass().add("lblText");

        lblPlayersNaam.setLayoutY(100);
        lblPlayersNaam.setLayoutX(150);
        lblPlayersKansen.setLayoutY(100);
        lblPlayersKansen.setLayoutX(400);

        Label lblTitel = new Label("Active Players");
        lblTitel.setLayoutX(175);
        lblTitel.setLayoutY(40);
        lblTitel.getStyleClass().add("lblTitel");
        this.getChildren().addAll(btnOk, lblPlayersNaam, lblPlayersKansen, lblTitel);

        for(int i = 1; i <= dc.geefAantalSpelers(); i++){
            Label temp = new Label(i + ")");
            temp.setLayoutX(95);
            temp.setLayoutY(100 + ((i-1) * 25));
            temp.getStyleClass().add("lblText");
            this.getChildren().addAll(temp);
        }
    }

    private void onClickButtonOk(ActionEvent event) {
        try {
            GameBoardController GameBoard = new GameBoardController(dc);
            Scene scene = new Scene(GameBoard, 900, 645);
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
