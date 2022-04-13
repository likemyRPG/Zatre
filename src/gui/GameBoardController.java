package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.List;
import java.util.Random;


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
    private boolean firstPiece = true, firstRound = true;
    private int[][] spelBord = new int[15][15];
    private int[][] ownPieces = new int[15][15];
    MediaPlayer mediaPlayer;
    Button btnSettings;
    ImageView imgRightArrow;
    ImageView imgLeftArrow;
    TextField txtPlayer;
    int spelerAanBeurt=-1;
    //endregion

    public GameBoardController(DomeinController dc) {
        try {
            this.dc = dc;
            buildGUI();
            addNonUsableTiles();
            generateButtons(3);
            addMusic();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buildGUI() {
        getStyleClass().add("bg-style");
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
            SpelbordGrid.getRowConstraints().add(new RowConstraints(30));
        }
        for (int i = 0; i < 15; i++) {
            SpelbordGrid.getColumnConstraints().add(new ColumnConstraints(30));
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
        this.getChildren().addAll(Title, Spelbord, SpelbordGrid, tbSelectionPiece, imageAmountOfPieces, lblAantalSteentjes, btnRandom, txtPlayer, sliderVolume, imgLeftArrow, imgRightArrow, Scoreboard, btnQuitGame, imgMusic);
        //endregion
    }

    private String geefSpelerAanBeurt() {
        spelerAanBeurt++;
        return dc.geefSpelersNaam().split(System.lineSeparator())[(spelerAanBeurt % dc.geefSpelersNaam().split(System.lineSeparator()).length)];
    }

    private void clickLeftArrow(MouseEvent mouseEvent) {
    }

    private void clickRightArrow(MouseEvent mouseEvent) {
        txtPlayer.setText(geefSpelerAanBeurt());

    }

    private void onClickButtonRandom(ActionEvent actionEvent) {
        //print the values of spelBord in console
        for (int i = 0; i < spelBord.length; i++) {
            for (int j = 0; j < spelBord[i].length; j++) {
                System.out.print(spelBord[i][j] + " ");
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
                    if (valueOfSelectedPiece != 0 && !alreadyUsed(row, column) && allowedPlacement(row, column) && allowedPlacementSum(row, column, valueOfSelectedPiece) && checkSpecialTileAllowed(row, column, valueOfSelectedPiece)) {
                        placePiece(row, column);
                    }
                }
            }
        }
    }


    private boolean checkSpecialTileAllowed(int row, int column, int valueOfSelectedPiece) {
        if(checkSpecialTile(row, column)) {
            return ((sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) >= 10 ) || (sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) >= 10 ));
        }else return true;
    }

    private boolean checkSpecialTile(int row, int column) {
        if(row ==column) return true;
        else if((row == 0) && (column == 6 || column == 8))
            return true;
        else if((row == 14) && (column == 5 || column == 8))
            return true;
        else if((column == 0 || column == 14) && (row == 6 || row == 8))
            return true;
        return (column == 14-row);

    }

    private boolean allowedPlacementSum(int row, int column, int valueOfSelectedPiece) {
        //return true if the value of sumOfContinousFollowingValuesH and the value of sumOfContinousFollowingValuesS are less than or equal to 12
        System.out.println("sumOfContinousFollowingValuesH: " + sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece));
        System.out.println("sumOfContinousFollowingValuesV: " + sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece));
        return ((sumOfContinousFollowingValuesH(row, column, valueOfSelectedPiece) <= 12 ) && (sumOfContinousFollowingValuesV(row, column, valueOfSelectedPiece) <= 12));
    }

    private int sumOfContinousFollowingValuesH(int row, int column, int valueOfSelectedPiece) {
        int sum1 = 0, sum2 = 0;
        int i = column;
        int j = row;
        spelBord[row][column] = valueOfSelectedPiece;
        while (spelBord[j][i] != 0 && spelBord[j][i] != 7) {
            sum1 += spelBord[j][i];
            if(i != 14)
                i++;
            else break;
        }
        i = column;
        j = row;
        while (spelBord[j][i] != 0 && spelBord[j][i] != 7) {
            sum2 += spelBord[j][i];
            if(i != 0)
                 i--;
            else break;
        }
        spelBord[row][column] = 0;
        return (sum1 + sum2) - valueOfSelectedPiece;
    }

    private int sumOfContinousFollowingValuesV(int row, int column, int valueOfSelectedPiece) {
        int sum1 = 0, sum2 = 0;
        int i = column;
        int j = row;
        spelBord[row][column] = valueOfSelectedPiece;
        while (spelBord[j][i] != 0 && spelBord[j][i] != 7) {
            sum1 += spelBord[j][i];
            if(j != 14)
                j++;
            else break;
        }
        i = column;
        j = row;
        while (spelBord[j][i] != 0 && spelBord[j][i] != 7) {
            sum2 += spelBord[j][i];
            if(j != 0)
                j--;
            else break;
        }
        spelBord[row][column] = 0;
        return (sum1 + sum2) - valueOfSelectedPiece;
    }

    private boolean allowedPlacement(int row, int column) {
        if(column != 14 && (spelBord[row][column+1]!=0 && spelBord[row][column+1]!= 7 && (ownPieces[row][column+1] == 0 || firstRound)))
            return true;
        else if(column != 0 && (spelBord[row][column-1]!=0 && spelBord[row][column-1]!=7 && (ownPieces[row][column-1] == 0 || firstRound)))
            return true;
        else if(row !=14 && spelBord[row+1][column]!=0 && spelBord[row+1][column]!=7 && (ownPieces[row+1][column] == 0 || firstRound))
            return true;
        else return row != 0 && ( spelBord[row-1][column] != 0 && spelBord[row-1][column] != 7 && (ownPieces[row-1][column] == 0 || firstRound));
    }

    private boolean alreadyUsed(int row, int column){
        return spelBord[row][column] != 0;
    }

    private void placePiece(int row, int column){
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/zatre_" + valueOfSelectedPiece + ".png")));
        image.setFitWidth(26);
        image.setFitHeight(26);
        SpelbordGrid.add(image, column, row); //add image to grid
        spelBord[row][column] = valueOfSelectedPiece;
        ownPieces[row][column] = valueOfSelectedPiece;
        SpelbordGrid.setHalignment(image, HPos.CENTER);
        SpelbordGrid.setValignment(image, VPos.CENTER);
        valueOfSelectedPiece = 0;
        Media sound = new Media(getClass().getResource("/gui/resources/zatre_Tile_Place.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(1);
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
                ownPieces[row][col] = 0;
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
            mediaPlayer.stop();
            StartMenuController StartMenu = new StartMenuController(dc); // <1>
            Scene scene = new Scene(StartMenu, 600, 400);
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
    //endregion
}