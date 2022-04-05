package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GameBoardController extends Pane {

    private DomeinController dc;
    GridPane SpelbordGrid = new GridPane();
    private int i =0;

    public GameBoardController(DomeinController dc) {
        try
        {
            this.dc = dc;
            buildGUI();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void buildGUI() {
        //getStyleClass().add("bg-style");

        double scaleFactor=2;

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
        SpelbordGrid.setGridLinesVisible(true);
        GridPane.setColumnSpan(SpelbordGrid,15);
        GridPane.setRowSpan(SpelbordGrid,15);
        for(int i=0; i<15;i++) {
            SpelbordGrid.getColumnConstraints().add(new ColumnConstraints(30));
        }
        for (int i=0; i<15;i++) {
            SpelbordGrid.getRowConstraints().add(new RowConstraints(30));
        }

        for(int i = 0; i >= SpelbordGrid.getColumnCount(); i++){
            for(int y =0; y >= SpelbordGrid.getRowCount(); y++){
                addButton(i, y);
                System.out.println(i + " " + y);
            }
        }
        SpelbordGrid.toFront();

        //region Blablka
        ImageView imgZatre1 = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_1.png")));
        imgZatre1.setFitWidth(30);
        imgZatre1.setFitHeight(30);

        ImageView imgZatre2 = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_2.png")));
        imgZatre2.setFitWidth(30);
        imgZatre2.setFitHeight(30);

        ImageView imgZatre3 = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_3.png")));
        imgZatre3.setFitWidth(30);
        imgZatre3.setFitHeight(30);

        ImageView imgZatre4 = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_4.png")));
        imgZatre4.setFitWidth(30);
        imgZatre4.setFitHeight(30);

        ImageView imgZatre5 = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_5.png")));
        imgZatre5.setFitWidth(30);
        imgZatre5.setFitHeight(30);

        ImageView imgZatre6 = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_6.png")));
        imgZatre6.setFitWidth(30);
        imgZatre6.setFitHeight(30);

        Button btnPiece1 = new Button();
        btnPiece1.setPrefWidth(30);
        btnPiece1.setPrefHeight(30);
        btnPiece1.setGraphic(imgZatre1);

        Button btnPiece2 = new Button();
        btnPiece2.setPrefWidth(30);
        btnPiece2.setPrefHeight(30);
        btnPiece2.setGraphic(imgZatre2);

        Button btnPiece3 = new Button();
        btnPiece3.setPrefWidth(30);
        btnPiece3.setPrefHeight(30);
        btnPiece3.setGraphic(imgZatre3);

        Button btnPiece4 = new Button();
        btnPiece4.setPrefWidth(30);
        btnPiece4.setPrefHeight(30);
        btnPiece4.setGraphic(imgZatre4);

        Button btnPiece5 = new Button();
        btnPiece5.setPrefWidth(30);
        btnPiece5.setPrefHeight(30);
        btnPiece5.setGraphic(imgZatre5);

        Button btnPiece6 = new Button();
        btnPiece6.setPrefWidth(30);
        btnPiece6.setPrefHeight(30);
        btnPiece6.setGraphic(imgZatre6);

        ToolBar tbSelectionPiece = new ToolBar(btnPiece1,btnPiece2,btnPiece3,btnPiece4,btnPiece5,btnPiece6);
        tbSelectionPiece.setLayoutX(130);
        tbSelectionPiece.setLayoutY(548);
        tbSelectionPiece.setPrefWidth(305);
        tbSelectionPiece.setPrefHeight(40);


        Button btnQuitGame = new Button("Quit game!");
        btnQuitGame.setMaxWidth(Double.MAX_VALUE);
        btnQuitGame.setOnAction(this::onClickButtonQuitGame);

        btnQuitGame.setLayoutX(610);
        btnQuitGame.setLayoutY(590);

        this.getChildren().addAll(Title,Spelbord,SpelbordGrid,imgZatre1,imgZatre2,imgZatre3,imgZatre4,imgZatre5,imgZatre6,btnPiece1,btnPiece2,btnPiece3,btnPiece4,btnPiece5,btnPiece6,tbSelectionPiece,btnQuitGame);

        //endregion
    }
    private void addButton(int row, int column) {
        i++;
        final Button temp = new Button("Button " + i);
        final int numButton= i;
        temp.setId("" + i);
        temp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("id(" + temp.getId()  + ") =  " + numButton);
            }
        });
        SpelbordGrid.add(temp, row, column);
    }

    public void onClickButtonQuitGame(ActionEvent event){
        try
        {
            StartMenuController StartMenu = new StartMenuController(dc); // <1>
            Scene scene = new Scene(StartMenu, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
