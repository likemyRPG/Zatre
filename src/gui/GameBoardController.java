package gui;

import domein.DomeinController;
import domein.Score;
import domein.Scoreblad;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import persistence.language;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class GameBoardController extends Pane {

    //region Variables
    private DomeinController dc;
    GridPane SpelbordGrid = new GridPane(); //Het spelbord
    ToolBar tbSelectionPiece; //De toolbar voor het selecteren van een stuk
    Label lblAantalSteentjes; //Het label voor het aantal steentjes dat nog over is
    Slider sliderVolume; //De slider voor het volume
    int index, amountOfPieces = 121; //Het aantal steentjes dat nog over is
    private int i = 0;
    private int piece = 0;
    private int valueOfSelectedPiece = 0;
    private boolean firstPiece = true, firstRound = true, endOfRound = false;
    MediaPlayer mediaPlayer;
    ImageView imgRightArrow;
    ImageView imgLeftArrow;
    Button btnGiveBack;
    GridPane Scoreboard;
    TextField txtPlayer;
    Label totalScore;
    boolean surrender = false;
    int spelerAanBeurt=-1, move = 0;
    Button btnSettings;
    language ln = new language();
    ResourceBundle rb = ln.taal();
    int round=0;
    //endregion

    public GameBoardController(DomeinController dc) {
        try {
            this.dc = dc;
            buildGUI();
            generateButtons(3);
            setScoreBoardLayout();
            disableSurrender(true);
            addMusic();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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

    private void buildGUI() {
        getStyleClass().add("bg-image");
        //region Create Gameboard
        ImageView Spelbord = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/Zatre_gameBoard_V2.png")));
        Spelbord.setLayoutX(75);
        Spelbord.setLayoutY(75);
        Spelbord.setScaleX(1.0063);
        Spelbord.setScaleY(1.0063);

        //in commentaar staat de waardes voor 100% scaling
/*        Spelbord.setScaleX(1.0063);
        Spelbord.setScaleY(1.0063);*/

        //in commentaar staat de waardes voor 100% scaling
/*        SpelbordGrid.setLayoutX(Spelbord.getLayoutX());
        SpelbordGrid.setLayoutY(Spelbord.getLayoutY());*/

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

//test est test et s f
        TextField Title = new TextField(rb.getString("title_gameboard"));
        Title.getStyleClass().add("Title");

        totalScore = new Label(String.format("%d", dc.getCurrentPlayer().getScoreblad().getTotalScore()));
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

        Title.setPrefWidth(Title.getText().length() * 28);
        //get the value of the center of the screen
        Title.setLayoutX(450 - Title.getPrefWidth() / 2);
        Title.setEditable(false);
        Title.setFocusTraversable(false);

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
        //set the first row its height 2 times the height of the other rows
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

        imgLeftArrow = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/left_arrow.png")));

        ImageView imgLeftArrow = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/left_arrow.png")));
        //scale image to fit the size of the textfield
        imgLeftArrow.setFitWidth(20);
        imgLeftArrow.setFitHeight(txtPlayer.getMinHeight());
        imgLeftArrow.setLayoutX(txtPlayer.getLayoutX() - imgLeftArrow.getFitWidth() - 5);
        imgLeftArrow.setLayoutY(txtPlayer.getLayoutY() + (txtPlayer.getMinHeight() / 2) + 2 - imgLeftArrow.getFitHeight() / 2);
        imgLeftArrow.setOnMouseClicked(this::clickLeftArrow);

        imgRightArrow = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/right_arrow.png")));
        imgRightArrow.setFitWidth(20);
        imgRightArrow.setFitHeight(txtPlayer.getMinHeight());
        imgRightArrow.setLayoutX(txtPlayer.getLayoutX() + txtPlayer.getMinWidth() + 5);
        imgRightArrow.setLayoutY(txtPlayer.getLayoutY() + (txtPlayer.getMinHeight() / 2) + 2 - imgRightArrow.getFitHeight() / 2);
        imgRightArrow.setOnMouseClicked(this::clickRightArrow);
        //endregion

        //add Button to give back the pieces
        btnGiveBack = new Button("Surrender");
        btnGiveBack.setMaxWidth(Double.MAX_VALUE);
        btnGiveBack.setOnAction(this::onClickButtonSurrender);
        btnGiveBack.setMinWidth(100);
        btnGiveBack.setMinHeight(50);
        btnGiveBack.setLayoutX(900 - btnGiveBack.getMinWidth() - 150);
        btnGiveBack.setLayoutY(645 - btnGiveBack.getMinHeight() - 10);

        //button to end the game
        Button btnEndGame = new Button("End Game");
        btnEndGame.setMaxWidth(Double.MAX_VALUE);
        btnEndGame.setOnAction(this::onClickButtonEndGame);
        btnEndGame.setMinWidth(100);
        btnEndGame.setMinHeight(50);
        btnEndGame.setLayoutX(900 - btnGiveBack.getMinWidth() - 280);
        btnEndGame.setLayoutY(645 - btnGiveBack.getMinHeight() - 10);

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
        btnQuitGame.setMinWidth(100);
        btnQuitGame.setMinHeight(50);

        btnQuitGame.setLayoutX(900 - btnQuitGame.getMinWidth() - 22);
        btnQuitGame.setLayoutY(645 - btnQuitGame.getMinHeight() - 10);
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
        this.getChildren().addAll(Spelbord, lblScore, totalScore, btnEndGame, SpelbordGrid, Title, tbSelectionPiece, imageAmountOfPieces, lblAantalSteentjes, btnRandom, txtPlayer, sliderVolume, imgLeftArrow, imgRightArrow, Scoreboard, btnQuitGame, imgMusic, btnGiveBack);        //endregion
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
        //use dc.voegPieceToe() with the ids of the buttons in the toolbar with a for loop
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
            //Get the last number of the id
            int lastNumber = ids[i] % 10;
            dc.voegPieceToe(lastNumber);
        }
        //delte the buttons in the toolbar
        tbSelectionPiece.getItems().clear();
        txtPlayer.setText(dc.getNextPlayer().getGebruikersnaam());
        updateToolbar();
    }

    private String geefSpelerAanBeurt() {
        spelerAanBeurt++;
        System.out.println(dc.getNextPlayer().getGebruikersnaam());
        return dc.getCurrentPlayer().getGebruikersnaam().split(System.lineSeparator())[(spelerAanBeurt % dc.getCurrentPlayer().getGebruikersnaam().split(System.lineSeparator()).length)];
    }

    private void clickLeftArrow(MouseEvent mouseEvent) {
        txtPlayer.setText(dc.getPreviousPlayer().getGebruikersnaam());
    }

    private void clickRightArrow(MouseEvent mouseEvent) {
        txtPlayer.setText(dc.getNextPlayer().getGebruikersnaam());
    }

    private void onClickButtonRandom(ActionEvent actionEvent) {
        try {
            mediaPlayer.stop();
            LeaderbordController Leaderbord = new LeaderbordController(dc); // <1>
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
                    placePiece(row, column);
                    firstPiece = false;
                }
            } else {
                if (isEmpty) {
                    if (dc.checkPlacement(row, column, firstRound, valueOfSelectedPiece)) {
                        dc.calculateScore(row, column, valueOfSelectedPiece, round);
                        placePiece(row, column);
                        txtPlayer.setText(dc.getCurrentPlayer().getGebruikersnaam());
                        totalScore.setText(String.format(("%d"), dc.getCurrentPlayer().getScoreblad().getTotalScore()));
                    }
                }
            }
        }
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
        updateToolbar();
    }

    private void updateScore() {
        int i = 1;
        //delete values of the scoreboard
        Node node = Scoreboard.getChildren().get(0);
        Scoreboard.getChildren().clear();
        Scoreboard.getChildren().add(0,node);
        setScoreBoardLayout();

        for (Score score : dc.getScoreblad().getScores()) {
            Label label1 =new Label(String.format("%s", score.isDoubleScore() ? "x" : " "));
            Label label2 =new Label(String.format("%s", printX(score.amountP10())));
            Label label3 =new Label(String.format("%s", printX(score.amountP11())));
            Label label4 =new Label(String.format("%s", printX(score.amountP12())));
            Label label5 =new Label(String.format("%s", score.getBonus()));
            Label label6 =new Label(String.format("%s", score.getScore()));
            Scoreboard.add(label1, 0, i);
            Scoreboard.add(label2, 1, i);
            Scoreboard.add(label3, 2,i);
            Scoreboard.add(label4, 3, i);
            Scoreboard.add(label5, 4, i);
            Scoreboard.add(label6, 5, i);
            GridPane.setHalignment(label1, HPos.CENTER);
            GridPane.setHalignment(label2, HPos.CENTER);
            GridPane.setHalignment(label3, HPos.CENTER);
            GridPane.setHalignment(label4, HPos.CENTER);
            GridPane.setHalignment(label5, HPos.CENTER);
            GridPane.setHalignment(label6, HPos.CENTER);
            i++;
        }
    }

    private String printX(int amount){
        String x = "";
        for(int i = 0; i < amount; i++){
            if(amount!=0) x+="x";
        }
        return x;
    }

    private void updateToolbar() {
        //If toolbar has no elements, add all pieces
        lblAantalSteentjes.setText("x" + amountOfPieces);
        if (tbSelectionPiece.getItems().isEmpty() && amountOfPieces > 0){
            if(!firstPiece){
                firstRound=false;
                disableSurrender(false);
            }
            dc.clearOwnPieces();
            generateButtons(2);
            move++;
            if(move%dc.geefAantalSpelers()==0) round++;
            dc.addScore(round);
            dc.setNextPlayer();
            dc.printScore();
            updateScore();
        }
        else if(amountOfPieces==0) gameOver();
    }

    private void gameOver() {
        //print the leaderbord
        for(int i =0; i < dc.geefAantalSpelers(); i++){
            System.out.println(dc.determineWinner().get(i).getGebruikersnaam());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Speler " + dc.determineWinner().get(0).getGebruikersnaam() + " heeft gewonnen!");
        alert.showAndWait();
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

    public void onClickButtonQuitGame(ActionEvent event) {
        try {
            mediaPlayer.stop();
            StartMenuController StartMenu = new StartMenuController(dc); // <1>
            Scene scene = new Scene(StartMenu, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
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
