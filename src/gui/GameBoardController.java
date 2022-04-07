package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GameBoardController extends Pane {

    private DomeinController dc;
    GridPane SpelbordGrid = new GridPane();
    ToolBar tbSelectionPiece;
    Label lblAantalSteentjes;
    private int i = 0;
    private int piece = 0;
    private int valueOfSelectedPiece = 0;

    public GameBoardController(DomeinController dc) {
        try {
            this.dc = dc;
            buildGUI();
            generateButtons();
            System.out.println(dc.geefSteentjesWeer());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buildGUI() {
        getStyleClass().add("bg-style");

        double scaleFactor = 2;


        //region create gameboard
        ImageView Title = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/Zatre_Spelbord_Title.png")));
        Title.setLayoutX(132);
        Title.setLayoutY(10);
        Title.setFitWidth(337);
        Title.setFitHeight(58);

        ImageView Spelbord = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/Zatre_gameBoard.png")));
        Spelbord.setLayoutX(60);
        Spelbord.setLayoutY(60);
        Spelbord.setScaleX(0.939);
        Spelbord.setScaleY(0.939);


        SpelbordGrid.setLayoutX(75);
        SpelbordGrid.setLayoutY(75);
        SpelbordGrid.setGridLinesVisible(false);
        GridPane.setColumnSpan(SpelbordGrid, 15);
        GridPane.setRowSpan(SpelbordGrid, 15);
        for (int i = 0; i < 15; i++) {
            SpelbordGrid.getColumnConstraints().add(new ColumnConstraints(30));
        }
        for (int i = 0; i < 15; i++) {
            SpelbordGrid.getRowConstraints().add(new RowConstraints(30));
        }


        SpelbordGrid.toFront();
        SpelbordGrid.setOnMouseClicked(this::clickGrid);
        //endregion

        //region configure ToolBar
        tbSelectionPiece = new ToolBar();
        tbSelectionPiece.setLayoutX(212);
        tbSelectionPiece.setLayoutY(548);
        tbSelectionPiece.setMinWidth(175);
        tbSelectionPiece.setMinHeight(50);
        //endregion

        //region add labels
        lblAantalSteentjes = new Label("Aantal Steentjes: " + dc.geefAantalSteentjes());
        lblAantalSteentjes.setLayoutX(400);
        lblAantalSteentjes.setLayoutY(555);
        lblAantalSteentjes.getStylesheets().add("lblText");
        //endregion

        Button btnGivePieces = new Button();
        btnGivePieces.setLayoutX(50);
        btnGivePieces.setLayoutY(548);
        btnGivePieces.setMinWidth(60);
        btnGivePieces.setMinHeight(50);
        btnGivePieces.setText("Geef steentjes");
        btnGivePieces.setOnAction(this::givePieces);

        //region Button Quit Game
        Button btnQuitGame = new Button("Quit game!");
        btnQuitGame.setMaxWidth(Double.MAX_VALUE);
        btnQuitGame.setOnAction(this::onClickButtonQuitGame);

        btnQuitGame.setLayoutX(610);
        btnQuitGame.setLayoutY(590);
        //endregion

        //Add to gameboard
        this.getChildren().addAll(Title, Spelbord, SpelbordGrid, tbSelectionPiece, lblAantalSteentjes, btnGivePieces,btnQuitGame);

        //endregion
    }

    private void clickGrid(MouseEvent e) {

        int column = (int) (e.getX() / 30);
        int row = (int) (e.getY() / 30);

        if(valueOfSelectedPiece!=0) {
            ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_" + valueOfSelectedPiece + ".png")));
            image.setFitWidth(28);
            image.setFitHeight(28);
            image.setLayoutY(1);
            image.setLayoutX(1);
            SpelbordGrid.add(image, column, row);
            updateToolbar();
            valueOfSelectedPiece = 0;
        }
    }

    private void updateToolbar() {
    //remove button by id from toolbar
        tbSelectionPiece.getItems().remove(i);

        lblAantalSteentjes.setText("Aantal Steentjes: " + dc.geefAantalSteentjes());
    }
    private void givePieces(ActionEvent e) {
        generateButtons();
    }

    private void generateButtons(){
        List<Integer> pieces = dc.getRandomPieces(3);
        int nrButton=1;

        for (Integer piece : pieces) {
            Button btnPiece = new Button();
            btnPiece.setPrefWidth(30);
            btnPiece.setPrefHeight(30);
            btnPiece.setId(toString().valueOf(nrButton)+ toString().valueOf(piece));
            btnPiece.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_" + piece + ".png"))));
            btnPiece.setOnAction(this::onClickButtonPiece);
            tbSelectionPiece.getItems().add(btnPiece);
            nrButton++;
        }
    }

    private void onClickButtonPiece(ActionEvent actionEvent) {
            Button btnPiece = (Button) actionEvent.getSource();
            piece = Integer.parseInt(btnPiece.getId());
            System.out.println(piece);
            //get the last digit of the int piece
            valueOfSelectedPiece = piece % 10;
    }

    public void onClickButtonQuitGame(ActionEvent event) {
        try {
            StartMenuController StartMenu = new StartMenuController(dc); // <1>
            Scene scene = new Scene(StartMenu, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

