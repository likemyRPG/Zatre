package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
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

public class GameStartScreen extends GridPane {
    private DomeinController dc;
    private Button btnOk;
    private Label lblPlayersKansen, lblPlayersNaam;
    private VBox vboxnaam, vboxgetal;

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
        getStyleClass().add("bg-image");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        ColumnConstraints col5 = new ColumnConstraints();
        ColumnConstraints col6 = new ColumnConstraints();

        col1.setPercentWidth(7);
        col2.setPercentWidth(5);
        col3.setPercentWidth(3);
        col4.setPercentWidth(10);
        col5.setPercentWidth(25);
        col6.setPercentWidth(50);

        getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);


        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        RowConstraints row7 = new RowConstraints();

        row1.setPercentHeight(5);
        row2.setPercentHeight(15);
        row3.setPercentHeight(5);
        row4.setPercentHeight(25);
        row5.setPercentHeight(10);
        row6.setPercentHeight(10);
        row7.setPercentHeight(30);

    getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);

        btnOk = new Button("Ok");
        btnOk.setMaxWidth(175);
        btnOk.setMinWidth(175);
        btnOk.setMaxHeight(35);
        add(btnOk, 4, 5);
        btnOk.setOnAction(this::onClickButtonOk);



        lblPlayersNaam = new Label(dc.geefSpelersNaam());
        lblPlayersNaam.getStyleClass().add("lblText");
        lblPlayersNaam.setMaxHeight(Double.MAX_VALUE);
        //lblPlayersNaam.setMinHeight(300);
        //lblPlayersNaam.setContentDisplay(ContentDisplay.BOTTOM);
        //add(lblPlayersNaam, 3, 3);

        VBox vboxnaam = new VBox(lblPlayersNaam);
        vboxnaam.setMaxHeight(Double.MAX_VALUE);
        add(vboxnaam, 3, 3);
        GridPane.setRowSpan(vboxnaam, 4);

        lblPlayersKansen = new Label(dc.geefSpelerKansen());
        lblPlayersKansen.getStyleClass().add("lblText");
        //lblPlayersKansen.setMinHeight(300);
        lblPlayersKansen.setMaxHeight(Double.MAX_VALUE);
        //add(lblPlayersKansen, 5,3);

        VBox vboxkans = new VBox(lblPlayersKansen);
        vboxkans.setMaxHeight(Double.MAX_VALUE);
        add(vboxkans, 5, 3);
        GridPane.setRowSpan(vboxkans, 4);

        Label lblTitel = new Label(rb.getString("activePlayers"));
        lblTitel.getStyleClass().add("Title");
        lblTitel.setTextAlignment(TextAlignment.CENTER);
        lblTitel.setMaxHeight(Double.MAX_VALUE);
        add(lblTitel, 3, 1);
        GridPane.setColumnSpan(lblTitel, 4);


        Label lblopsom1 = new Label("1) ");
        lblopsom1.getStyleClass().add("lblText");
        lblopsom1.setVisible(true);

        Label lblopsom2 = new Label("2) ");
        lblopsom2.getStyleClass().add("lblText");
        lblopsom2.setVisible(true);

        Label lblopsom3 = new Label("3) ");
        lblopsom3.getStyleClass().add("lblText");
        lblopsom3.setVisible(false);

        Label lblopsom4 = new Label("4) ");
        lblopsom4.getStyleClass().add("lblText");
        lblopsom4.setVisible(false);

        if(dc.geefAantalSpelers() == 3) {
            lblopsom3.setVisible(true);
        }

        if(dc.geefAantalSpelers() == 4) {
            lblopsom3.setVisible(true);
            lblopsom4.setVisible(true);
        }

        VBox vboxgetal = new VBox(lblopsom1, lblopsom2, lblopsom3, lblopsom4);
        vboxgetal.setSpacing(17);
        add(vboxgetal, 1, 3);
        GridPane.setRowSpan(vboxgetal, 4);
    }

    private void onClickButtonOk(ActionEvent event) {
        try {
            GameBoardController GameBoard = new GameBoardController(dc);
            Scene scene = new Scene(GameBoard, 900, 645);
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
