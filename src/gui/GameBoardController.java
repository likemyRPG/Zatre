package gui;

import domein.DomeinController;
import domein.Spel;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
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

import java.util.Arrays;
import java.util.List;


public class GameBoardController extends Pane {

    //region Variables
    private DomeinController dc;
    GridPane SpelbordGrid = new GridPane();
    ToolBar tbSelectionPiece;
    Label lblAantalSteentjes;
    int index, amountOfPieces = 121;
    private int i = 0;
    private int piece = 0;
    private int valueOfSelectedPiece = 0;
    private boolean firstPiece = true, firstRound = true;
    private int[][] spelBord = new int[15][15];
    private int[][] ownPieces = new int[15][15];
    //endregion

    public GameBoardController(DomeinController dc) {
        try {
            this.dc = dc;
            buildGUI();
            addNonUsableTiles();
            generateButtons(3);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buildGUI() {
/*        getStyleClass().add("bg-style");*/

        //region Create Gameboard
        ImageView Title = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/Zatre_Spelbord_Title.png")));
        Title.setLayoutX(132);
        Title.setLayoutY(10);
        Title.setFitWidth(337);
        Title.setFitHeight(58);

        ImageView Spelbord = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/Zatre_gameBoard_V2.png")));
        Spelbord.setLayoutX(75);
        Spelbord.setLayoutY(75);

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

        //region Configure ToolBar
        tbSelectionPiece = new ToolBar();
        tbSelectionPiece.setLayoutX(212);
        tbSelectionPiece.setLayoutY(548);
        tbSelectionPiece.setMinWidth(175);
        tbSelectionPiece.setMinHeight(50);
        //endregion

        //region Aantal Steentjes
        ImageView imageAmountOfPieces = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/zatre_1.png")));
        imageAmountOfPieces.minWidth(30);
        imageAmountOfPieces.minHeight(30);
        imageAmountOfPieces.setLayoutX(10);
        imageAmountOfPieces.setLayoutY(606);



        lblAantalSteentjes = new Label("x" + dc.geefAantalSteentjes());
        lblAantalSteentjes.setLayoutX(45);
        lblAantalSteentjes.setLayoutY(608);
        lblAantalSteentjes.getStyleClass().add("lblText");
        //endregion

        //region Scoreboard

        GridPane Scoreboard = new GridPane();
        Scoreboard.setLayoutX(580);
        Scoreboard.setLayoutY(100);
        int width = 250;
        int height = 450;
        Scoreboard.setMinWidth(width);
        Scoreboard.setMinHeight(height);
        //make cells use all available space
        Scoreboard.setGridLinesVisible(true);
        Scoreboard.setStyle("-fx-border-color: white");
        GridPane.setColumnSpan(Scoreboard, 5);
        GridPane.setRowSpan(Scoreboard, 5);
        int amountOfColumns = 6;
        int amountOfRows = 22;
        for (int i = 0; i < amountOfColumns; i++) {
            Scoreboard.getColumnConstraints().add(new ColumnConstraints(width / amountOfColumns));
        }
        for (int i = 0; i < amountOfRows; i++) {
            Scoreboard.getRowConstraints().add(new RowConstraints(height / amountOfRows));
        }
/*        Scoreboard.add(, 1, 1);*/

        //endregion

        //region Players
        TextField txtPlayer1 = new TextField("Player 1");
        txtPlayer1.getStyleClass().add("txtPlayer");
        txtPlayer1.setAlignment(Pos.CENTER);
        txtPlayer1.setEditable(false);
        txtPlayer1.setMinWidth(150);
        txtPlayer1.setPrefWidth(150);
        txtPlayer1.setMinHeight(20);
        txtPlayer1.setLayoutX(Scoreboard.getLayoutX() + Scoreboard.getMinWidth() / 2 - txtPlayer1.getMinWidth() / 2);
        txtPlayer1.setLayoutY(Scoreboard.getLayoutY() - (txtPlayer1.getMinHeight()+5));

        ImageView imgLeftArrow = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/left_arrow.png")));
        //scale image to fit the size of the textfield
        imgLeftArrow.setFitWidth(20);
        imgLeftArrow.setFitHeight(txtPlayer1.getMinHeight());
        imgLeftArrow.setLayoutX(txtPlayer1.getLayoutX() - imgLeftArrow.getFitWidth() - 5);
        imgLeftArrow.setLayoutY(txtPlayer1.getLayoutY() + (txtPlayer1.getMinHeight() / 2)+2 - imgLeftArrow.getFitHeight() / 2);
        imgLeftArrow.setOnMouseClicked(this::clickLeftArrow);

        ImageView imgRightArrow = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/right_arrow.png")));
        imgRightArrow.setFitWidth(20);
        imgRightArrow.setFitHeight(txtPlayer1.getMinHeight());
        imgRightArrow.setLayoutX(txtPlayer1.getLayoutX() + txtPlayer1.getMinWidth() + 5);
        imgRightArrow.setLayoutY(txtPlayer1.getLayoutY() + (txtPlayer1.getMinHeight() / 2)+2 - imgRightArrow.getFitHeight() / 2);
        imgRightArrow.setOnMouseClicked(this::clickRightArrow);
        //endregion

        //region Random Button

        Button btnRandom = new Button("Random");
        btnRandom.setMaxWidth(Double.MAX_VALUE);
        btnRandom.setLayoutX(10);
        btnRandom.setLayoutY(550);
        btnRandom.setOnAction(this::onClickButtonRandom);
        //endregion

        //region Button Quit Game
        Button btnQuitGame = new Button("Quit game!");
        btnQuitGame.setMaxWidth(Double.MAX_VALUE);
        btnQuitGame.setOnAction(this::onClickButtonQuitGame);

        btnQuitGame.setLayoutX(900 - btnQuitGame.getMaxWidth() - 10);
        btnQuitGame.setLayoutY(645 - btnQuitGame.getMaxHeight() - 10);
        //endregion

        //region Add To Gameboard
        this.getChildren().addAll(Title, Spelbord, SpelbordGrid, tbSelectionPiece, imageAmountOfPieces, lblAantalSteentjes,btnRandom,txtPlayer1,imgLeftArrow,imgRightArrow,Scoreboard,btnQuitGame);
        //endregion
    }

    private void clickLeftArrow(MouseEvent mouseEvent) {
    }

    private void clickRightArrow(MouseEvent mouseEvent) {
    }

    private void onClickButtonRandom(ActionEvent actionEvent) {
        //print the values of spelBord in console
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(spelBord[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }

    private void clickGrid(MouseEvent e) {

        int column = (int) (e.getX() / 30);
        int row = (int) (e.getY() / 30);
        boolean isEmpty = true;
        System.out.println(column + " " + row);

        for (Node node : SpelbordGrid.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                if (node instanceof ImageView) {
                    isEmpty = false;
                }
            }
        }
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

    private boolean allowedPlacement(int column, int row) {
        if(column != 14 || column != 0) {
            if ((spelBord[column + 1][row] != 0 && spelBord[column + 1][row] != 7 && (ownPieces[column + 1][row] == 0 | firstRound)))
                return true;
            else if ((spelBord[column - 1][row] != 0 && spelBord[column - 1][row] != 7 && (ownPieces[column - 1][row] == 0 | firstRound)))
                return true;
        }else if(row != 14 || row != 0) {
            if ((spelBord[column][row + 1] != 0 && spelBord[column][row + 1] != 7 && (ownPieces[column][row + 1] == 0 | firstRound)))
                return true;
            else
                return ((spelBord[column][row - 1] != 0 && spelBord[column][row - 1] != 7 && (ownPieces[column][row - 1] == 0 || firstRound)));
        }
        return false;
    }

    private boolean alreadyUsed(int column, int row){
        return spelBord[column][row] != 0;
    }

    private void placePiece(int column, int row){
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_" + valueOfSelectedPiece + ".png")));
        image.setFitWidth(26);
        image.setFitHeight(26);
        SpelbordGrid.add(image, column, row);
        spelBord[column][row] = valueOfSelectedPiece;
        ownPieces[column][row] = valueOfSelectedPiece;
        SpelbordGrid.setHalignment(image, HPos.CENTER);
        SpelbordGrid.setValignment(image, VPos.CENTER);
        valueOfSelectedPiece = 0;
        updateToolbar();
    }

    private void updateToolbar() {
        //If toolbar has no elements, add all pieces
        tbSelectionPiece.getItems().remove(index);
        amountOfPieces--;
        lblAantalSteentjes.setText("x" + amountOfPieces);
        if (tbSelectionPiece.getItems().isEmpty() && amountOfPieces > 0){
            if(!firstPiece) firstRound=false;
            clearOwnPieces();
            generateButtons(2);
        }
        else if(amountOfPieces==0)
            System.out.println("Game ended");
    }

    private void clearOwnPieces() {
        for (int col = 0; col < ownPieces.length; col++) {
            for (int row = 0; row < ownPieces.length; row++) {
                ownPieces[col][row] = 0;
            }
        }
    }

    private void generateButtons(int amount){
        List<Integer> pieces = dc.getRandomPieces(amount);
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
        pieces.clear();
    }

    private void onClickButtonPiece(ActionEvent actionEvent) {
        Button btnPiece = (Button) actionEvent.getSource();
        piece = Integer.parseInt(btnPiece.getId());

        valueOfSelectedPiece = piece % 10;
        //Get the location of the button in the toolbar element
        index = tbSelectionPiece.getItems().indexOf(btnPiece);
        //Delete background color of all the buttons in the toolbar
        for (Node node : tbSelectionPiece.getItems()) {
            node.setStyle("-fx-background-color: #1d1d1d;");
        }
        //Make button selected
        btnPiece.setStyle("-fx-background-color: white;");
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

    private void addNonUsableTiles(){
        spelBord[0][0]=7;
        spelBord[0][1]=7;
        spelBord[0][2]=7;
        spelBord[0][3]=7;
        spelBord[0][7]=7;
        spelBord[0][11]=7;
        spelBord[0][12]=7;
        spelBord[0][13]=7;
        spelBord[0][14]=7;
        spelBord[1][0]=7;
        spelBord[1][14]=7;
        spelBord[2][0]=7;
        spelBord[2][14]=7;
        spelBord[3][0]=7;
        spelBord[3][14]=7;
        spelBord[7][0]=7;
        spelBord[7][14]=7;
        spelBord[11][0]=7;
        spelBord[11][14]=7;
        spelBord[12][0]=7;
        spelBord[12][14]=7;
        spelBord[13][0]=7;
        spelBord[13][14]=7;
        spelBord[14][0]=7;
        spelBord[14][1]=7;
        spelBord[14][2]=7;
        spelBord[14][3]=7;
        spelBord[14][7]=7;
        spelBord[14][11]=7;
        spelBord[14][12]=7;
        spelBord[14][13]=7;
        spelBord[14][14]=7;
    }
}