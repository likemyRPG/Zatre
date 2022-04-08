package gui;

import domein.DomeinController;
import domein.Spel;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;


public class GameBoardController extends Pane {

    private DomeinController dc;
    GridPane SpelbordGrid = new GridPane();
    ToolBar tbSelectionPiece;
    Label lblAantalSteentjes;
    int index, amountOfPieces = 121;
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

        //region Button Quit Game
        Button btnQuitGame = new Button("Quit game!");
        btnQuitGame.setMaxWidth(Double.MAX_VALUE);
        btnQuitGame.setOnAction(this::onClickButtonQuitGame);

        btnQuitGame.setLayoutX(610);
        btnQuitGame.setLayoutY(590);
        //endregion

        //region ImageView
        ImageView imageAmountOfPieces = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/zatre_1.png")));
        imageAmountOfPieces.minWidth(30);
        imageAmountOfPieces.minHeight(30);
        imageAmountOfPieces.setLayoutX(10);
        imageAmountOfPieces.setLayoutY(590);
        //endregion

        //region Players
        TextField txtPlayer1 = new TextField("Player 1");
        txtPlayer1.getStyleClass().add("txtPlayer");
        txtPlayer1.setAlignment(Pos.CENTER);
        txtPlayer1.setEditable(false);
        txtPlayer1.setMinWidth(50);
        txtPlayer1.setPrefWidth(50);
        txtPlayer1.setLayoutX(580);
        txtPlayer1.setLayoutY(74);
        //endregion



        //region Add to gameboard
        this.getChildren().addAll(Title, Spelbord, SpelbordGrid, tbSelectionPiece, imageAmountOfPieces, lblAantalSteentjes,txtPlayer1,btnQuitGame);

        //endregion
    }

    private void clickGrid(MouseEvent e) {

        int column = (int) (e.getX() / 30);
        int row = (int) (e.getY() / 30);
        boolean isEmpty = true;

        for (Node node : SpelbordGrid.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                if (node instanceof ImageView) {
                    isEmpty = false;
                }
            }
        }
            //Check if row and col are a part of a 2 dimensional array
        if(firstPiece){
            if(column ==7 && row == 7){
                placePiece(column, row);
                firstPiece = false;
            }
        }else{
            if(isEmpty){
                if(valueOfSelectedPiece != 0 && !alreadyUsed(column, row) && allowedPlacement(column, row)){
                    placePiece(column, row);
                }
            }
        }
    }

    private void updateToolbar() {
        //If toolbar has no elements, add all pieces
        tbSelectionPiece.getItems().remove(index);
        amountOfPieces--;
        lblAantalSteentjes.setText("x" + amountOfPieces);
        if (tbSelectionPiece.getItems().isEmpty() && amountOfPieces > 0)
            generateButtons(2);
        else if(amountOfPieces==0)
            System.out.println("Game ended");
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
            btnPiece.setStyle("-fx-background-color: #1d1d1d;");
            tbSelectionPiece.getItems().add(btnPiece);
            nrButton++;
        }
    }

    private void onClickButtonPiece(ActionEvent actionEvent) {
            Button btnPiece = (Button) actionEvent.getSource();
            piece = Integer.parseInt(btnPiece.getId());

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
