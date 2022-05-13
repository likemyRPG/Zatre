package gui;

import domein.DomeinController;
import domein.Score;
import domein.Scoreblad;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.util.Duration;
import persistence.language;

import java.io.File;
import java.util.*;


public class GameBoardController extends Pane {

    //region Variables

    language ln = new language();
    ResourceBundle rb = ln.taal();
    private DomeinController dc;
    private int i = 0;
    private int piece = 0;
    private int valueOfSelectedPiece = 0;
    private boolean firstPiece = true, firstRound = true, endOfRound = false;

    int index, amountOfPieces = 121, spelerAanBeurt=-1, move = 0;

    GridPane SpelbordGrid = new GridPane(); //Het spelbord
    GridPane Scoreboard;
    ToolBar tbSelectionPiece; //De toolbar voor het selecteren van een stuk
    Label lblAantalSteentjes, totalScore,txtTimer;
    Slider sliderVolume; //De slider voor het volume
    MediaPlayer mediaPlayer;
    ImageView imgLeftArrow, Spelbord,imgRightArrow;
    Button btnGiveBack, btnEndGame;

    TextField txtPlayer;
    //endregion

    public GameBoardController(DomeinController dc) {
        try {
            this.dc = dc;
            buildGUI();
            setSpelbordScaling();
            generateButtons(3);
            setScoreBoardLayout();
            disableSurrender(true);
            addMusic();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buildGUI() {
        getStyleClass().add("bg-image");
        //region Create Gameboard
        Spelbord = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/Zatre_gameBoard_V2.png")));
        Spelbord.setLayoutX(75);
        Spelbord.setLayoutY(75);
        Spelbord.setScaleX(1.0063);
        Spelbord.setScaleY(1.0063);
        SpelbordGrid.setLayoutX(Spelbord.getLayoutX()-3);
        SpelbordGrid.setLayoutY(Spelbord.getLayoutY()-3);
        SpelbordGrid.setGridLinesVisible(false);
        GridPane.setColumnSpan(SpelbordGrid, 15);
        GridPane.setRowSpan(SpelbordGrid, 15);
        for (int i = 0; i < 15; i++) {
            SpelbordGrid.getRowConstraints().add(new RowConstraints(30));
        }
        for (int i = 0; i < 15; i++) {
            SpelbordGrid.getColumnConstraints().add(new ColumnConstraints(30));
        }
        SpelbordGrid.toFront();
        SpelbordGrid.setOnMouseClicked(this::clickGrid);
        //endregion

        TextField Title = new TextField(rb.getString("title_gameboard"));
        Title.getStyleClass().add("Title");

        totalScore = new Label(String.format("%d", dc.getScoreCurrentPlayer()));
        totalScore.getStyleClass().add("totalScore");
        totalScore.setFocusTraversable(false);
        totalScore.setLayoutX(760);
        totalScore.setLayoutY(550);
        totalScore.setMaxWidth(50);

        Label lblScore = new Label("Score: ");
        lblScore.getStyleClass().add("totalScore");
        lblScore.setFocusTraversable(false);
        lblScore.setLayoutX(700);
        lblScore.setLayoutY(550);

        Title.setPrefWidth(370);
        Title.setLayoutX(425 - Title.getPrefWidth() / 2);
        Title.setEditable(false);
        Title.setFocusTraversable(false);

        //region Configure ToolBar
        tbSelectionPiece = new ToolBar();
        tbSelectionPiece.setLayoutX(212);
        tbSelectionPiece.setLayoutY(548);
        tbSelectionPiece.setMinWidth(175);
        tbSelectionPiece.setMinHeight(50);
        //endregion

        txtTimer = new Label();
        txtTimer.setText("3");
        txtTimer.setVisible(false);
        txtTimer.setTextFill(Color.color(0.72, 0.55, 0.36));
        txtTimer.setFont(Font.font("OCR A Extended", FontWeight.BOLD, 20));
        txtTimer.setEffect(new DropShadow(+25d, 0d, 0d, Color.color(0.16, 0.16, 0.17)));
        txtTimer.setLayoutX(tbSelectionPiece.getLayoutX() + tbSelectionPiece.getMinWidth() + 10);
        txtTimer.setLayoutY(tbSelectionPiece.getLayoutY() + tbSelectionPiece.getMinHeight() / 2 - txtTimer.getPrefHeight() / 2);

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
        Scoreboard = new GridPane();
        Scoreboard.setLayoutX(580);
        Scoreboard.setLayoutY(100);
        int width = 230;
        int height = 420;
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
        Scoreboard.getRowConstraints().get(0).setPrefHeight(2 * height / amountOfRows);
        Scoreboard.getStyleClass().add("grdScoreBord");

        //endregion

        //region Players
        txtPlayer = new TextField(geefSpelerAanBeurt());
        txtPlayer.getStyleClass().add("txtPlayer");
        txtPlayer.setAlignment(Pos.CENTER);
        txtPlayer.setEditable(false);
        txtPlayer.setMinWidth(150);
        txtPlayer.setPrefWidth(150);
        txtPlayer.setMinHeight(20);
        txtPlayer.setLayoutX(Scoreboard.getLayoutX() + Scoreboard.getMinWidth() / 2 - txtPlayer.getMinWidth() / 2);
        txtPlayer.setLayoutY(Scoreboard.getLayoutY() - (txtPlayer.getMinHeight() + 5));
        //endregion

        //add Button to give back the pieces
        btnGiveBack = new Button(rb.getString("surrender"));
        btnGiveBack.setMaxWidth(Double.MAX_VALUE);
        btnGiveBack.setOnAction(this::onClickButtonSurrender);
        btnGiveBack.setMinWidth(100);
        btnGiveBack.setMinHeight(50);
        btnGiveBack.setLayoutX(900 - btnGiveBack.getMinWidth() - 150);
        btnGiveBack.setLayoutY(645 - btnGiveBack.getMinHeight() - 10);

        //button to end the game
        btnEndGame = new Button(rb.getString("endgame"));
        btnEndGame.setMaxWidth(Double.MAX_VALUE);
        btnEndGame.setOnAction(this::onClickButtonEndGame);
        btnEndGame.setMinWidth(100);
        btnEndGame.setMinHeight(50);
        btnEndGame.setLayoutX(900 - btnEndGame.getMinWidth() - 22);
        btnEndGame.setLayoutY(645 - btnEndGame.getMinHeight() - 10);

        //region Random Button

        Button btnRandom = new Button("Random");
        btnRandom.setMaxWidth(Double.MAX_VALUE);
        btnRandom.setLayoutX(10);
        btnRandom.setLayoutY(550);
        btnRandom.setOnAction(this::onClickButtonRandom);
        //endregion


        //region Music button
        ImageView imgMusic = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/pause.png")));
        imgMusic.setFitWidth(20);
        imgMusic.setFitHeight(20);
        imgMusic.setLayoutX(870);
        imgMusic.setLayoutY(560);
        imgMusic.setOnMouseClicked(this::clickMusic);
        //endregion

        //region create sliderVolume
        sliderVolume = new Slider();
        sliderVolume.setOrientation(Orientation.VERTICAL);
        sliderVolume.setMin(0);
        sliderVolume.setMax(100);
        sliderVolume.setValue(3);
        sliderVolume.setLayoutX(imgMusic.getLayoutX() + 3);
        sliderVolume.setLayoutY(imgMusic.getLayoutY() - 140);
        sliderVolume.setOnMouseClicked(this::changeMusicVolume);
        //endregion

        //region Add To Gameboard
        this.getChildren().addAll(Spelbord, txtTimer, lblScore, totalScore, btnEndGame, SpelbordGrid, Title, tbSelectionPiece, imageAmountOfPieces, lblAantalSteentjes, btnRandom, txtPlayer, sliderVolume, Scoreboard, imgMusic, btnGiveBack);
        //endregion
    }


    //Get the scaling of the users screen and set the scaling of the gameboard
    private void setSpelbordScaling() {
        //Get the scaling of the users screen
        int scaling = java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        //Set the scaling of the gameboard
        if(scaling == 96) {
            Spelbord.setScaleX(1.0063);
            Spelbord.setScaleY(1.0063);

            SpelbordGrid.setLayoutX(Spelbord.getLayoutX());
            SpelbordGrid.setLayoutY(Spelbord.getLayoutY());
        } else if(scaling == 120) {
            Spelbord.setScaleX(1.0063);
            Spelbord.setScaleY(1.0063);

            SpelbordGrid.setLayoutX(Spelbord.getLayoutX()-3);
            SpelbordGrid.setLayoutY(Spelbord.getLayoutY()-3);
        } else if(scaling == 144) {
            SpelbordGrid.setScaleX(1);
            SpelbordGrid.setScaleY(1);
        } else if(scaling == 192) {
        }
    }

    //Disable the surrender button
    private void disableSurrender(boolean choice) {
        btnGiveBack.setDisable(choice);
    }

    private void setScoreBoardLayout() {
        Label label1 = new Label("x2");
        Label label2 = new Label("10\n (1pt)");
        Label label3 = new Label("11\n (2pt)");
        Label label4 = new Label("12\n (4pt)");
        Label label5 = new Label("Bonus");
        Label label6 = new Label("Total");
        Scoreboard.add(label1, 0,  0);
        Scoreboard.add(label2, 1, 0);
        Scoreboard.add(label3, 2, 0);
        Scoreboard.add(label4, 3, 0);
        Scoreboard.add(label5, 4,  0);
        Scoreboard.add(label6, 5,  0);
        GridPane.setHalignment(label1, HPos.CENTER);
        GridPane.setHalignment(label2, HPos.CENTER);
        GridPane.setHalignment(label3, HPos.CENTER);
        GridPane.setHalignment(label4, HPos.CENTER);
        GridPane.setHalignment(label5, HPos.CENTER);
        GridPane.setHalignment(label6, HPos.CENTER);

    }

    private void onClickButtonEndGame(ActionEvent event) {
        //get an allert that asks if the player wants to end the game
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("End Game");
        alert.setHeaderText("End Game");
        alert.setContentText("Are you sure you want to end the game? The results will be ");
        alert.showAndWait();
        //When the user clicks ok, the game ends
        if (alert.getResult() == ButtonType.OK) gameOver();

    }

    private void onClickButtonSurrender(ActionEvent event) {
        //get the ids of the buttons in the toolbar
        int[] ids = new int[tbSelectionPiece.getItems().size()];
        for (int i = 0; i < tbSelectionPiece.getItems().size(); i++) {
            ids[i] = Integer.parseInt(tbSelectionPiece.getItems().get(i).getId());
        }
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
            //Get the last number of the id
            int lastNumber = ids[i] % 10;
            dc.voegPieceToe(lastNumber);
        }
        //delte the buttons in the toolbar
        tbSelectionPiece.getItems().clear();
        updateToolbarScoreBoard();
    }

    private String geefSpelerAanBeurt() {
        spelerAanBeurt++;
        System.out.println(dc.getNextPlayerUsername());
        return dc.getNameCurrentPlayer().split(System.lineSeparator())[(spelerAanBeurt % dc.getNameCurrentPlayer().split(System.lineSeparator()).length)];
    }

    private void onClickButtonRandom(ActionEvent actionEvent) {

    }

    private void clickGrid(MouseEvent e) {
        int column = (int) (e.getX() / 30);
        int row = (int) (e.getY() / 30);
        boolean isEmpty = true;
        System.out.println(row + " " + column);

        if (valueOfSelectedPiece == 0) {
            tbSelectionPiece.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selecteer een steen");
            alert.setHeaderText(null);
            if (firstRound) {
                alert.setContentText("Selecteer een steen om te beginnen met spelen! De eerste steen mag enkel in het middelste vakje worden geplaatst.");
            } else {
                alert.setContentText("Selecteer een steen om te plaatsen.");
            }
            alert.showAndWait();
        } else {
            tbSelectionPiece.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            for (Node node : SpelbordGrid.getChildren()) {
                if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                    if (node instanceof ImageView) {
                        isEmpty = false;
                    }
                }
            }
            if (firstPiece) {
                if (column == 7 && row == 7) {
                    firstPiece = false;
                    score(row, column);
                }
            } else {
                if (isEmpty) {
                    if (dc.checkPlacement(row, column, firstRound, valueOfSelectedPiece)) {
                        score(row, column);
                        txtPlayer.setText(dc.getNameCurrentPlayer());
                        totalScore.setText(String.format(("%d"), dc.getScoreCurrentPlayer()));
                    }
                }
            }
        }
    }

    private void score(int row, int column){
        dc.calculateScore(row, column, valueOfSelectedPiece);
        placePiece(row, column);
        updateScore();
    }

    private void placePiece(int row, int column){
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_" + valueOfSelectedPiece + ".png")));
        image.setFitWidth(26);
        image.setFitHeight(26);
        SpelbordGrid.add(image, column, row); //add image to grid
        dc.setValuesGameBoard(row, column, valueOfSelectedPiece);
        SpelbordGrid.setHalignment(image, HPos.CENTER);
        SpelbordGrid.setValignment(image, VPos.CENTER);
        valueOfSelectedPiece = 0;
        Media sound = new Media(getClass().getResource("/gui/resources/zatre_Tile_Place.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(1);
        tbSelectionPiece.getItems().remove(index);
        amountOfPieces--;
        updateToolbarScoreBoard();
    }

    private void updateScore() {
        int i = 1;
        //delete values of the scoreboard
        Node node = Scoreboard.getChildren().get(0);
        Scoreboard.getChildren().clear();
        Scoreboard.getChildren().add(0,node);
        setScoreBoardLayout();

        int[][] values = dc.printScoreBoard();
        for(int j = 0; j < values.length; j++){
            for(int k = 0; k < values[j].length-2; k++){
                Label label = new Label(String.format("%s", printX(values[j][k])));
                Scoreboard.add(label, k, j+1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
            for(int k = values[j].length-2; k < values[j].length; k++){
                Label label = new Label(String.format("%d", values[j][k]));
                Scoreboard.add(label, k, j+1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
    }

    private String printX(int amount){
        String x = "";
        for(int i = 0; i < amount; i++){
            if(amount!=0) x+="x";
        }
        return x;
    }

    private void updateToolbarScoreBoard() {
        //If toolbar has no elements, add all pieces
        lblAantalSteentjes.setText("x" + amountOfPieces);
        if (tbSelectionPiece.getItems().isEmpty() && amountOfPieces > 0){
            if(!firstPiece){
                firstRound=false;
                disableSurrender(false);
            }
            waitForNextPlayer();
        }
        else if(amountOfPieces==0) gameOver();
    }

    // Wait for next player to play
    private void waitForNextPlayer(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(txtTimer.getText().equals("3")){
                    txtTimer.setText("2");
                }
                else if(txtTimer.getText().equals("2")) {
                    txtTimer.setText("1");
                } else if (txtTimer.getText().equals("1")) {
                    txtTimer.setText("0");
                } else {
                    txtTimer.setVisible(false);
                }
            }
        }));

        Timer timer = new Timer();
        txtTimer.setVisible(true);
        txtTimer.setText("3");
        timeline.setCycleCount(3);
        timeline.play();

        disableSurrender(true);
        btnEndGame.setDisable(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    dc.clearOwnPieces();
                    generateButtons(2);
                    move++;
                    dc.setNextPlayer();
                    txtPlayer.setText(dc.getNameCurrentPlayer());
                    totalScore.setText(String.format(("%d"), dc.getScoreCurrentPlayer()));
                    disableSurrender(false);
                    btnEndGame.setDisable(false);
                    txtTimer.setVisible(false);
                    dc.printScore();
                    updateScore();
                });
            }
        }, 3000);
    }

    private void gameOver() {
        dc.determineWinner();
        dc.giveReward();
        try {
            mediaPlayer.stop();
            LeaderbordController Leaderbord = new LeaderbordController(dc);
            Scene scene = new Scene(Leaderbord, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void generateButtons(int amount){
        if(dc.geefAantalSteentjes() < amount){
            gameOver();
        }
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

    //region Music
    private void addMusic() {
        File musicFolder = new File("src/gui/resources/music");
        File[] musicFiles = musicFolder.listFiles();
        Random random = new Random();
        int randomNumber = random.nextInt(musicFiles.length);
        String musicFile = musicFiles[randomNumber].getAbsolutePath();
        mediaPlayer = new MediaPlayer(new Media(new File(musicFile).toURI().toString()));
        mediaPlayer.setVolume(0.03);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void changeMusicVolume(MouseEvent event) {
        mediaPlayer.setVolume(sliderVolume.getValue() / 100);
    }

    private void clickMusic(MouseEvent event) {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            //set Imageview to play
            ImageView imgMusic = (ImageView) event.getSource();
            imgMusic.setImage(new Image(getClass().getResourceAsStream("/gui/resources/play.png")));
        }
        else {
            mediaPlayer.play();
            //set Imageview to pause
            ImageView imgMusic = (ImageView) event.getSource();
            imgMusic.setImage(new Image(getClass().getResourceAsStream("/gui/resources/pause.png")));
        }
    }
    //endregion
}