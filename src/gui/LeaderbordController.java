package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class LeaderbordController extends Pane {

    private DomeinController dc;
    Rectangle rctFirst = new Rectangle();
    Rectangle rctSecond = new Rectangle();
    Rectangle rctThird = new Rectangle();
    HBox Podium = new HBox();

    public LeaderbordController(DomeinController dc) {
        try {
            this.dc = dc;
            buildGUI();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private void buildGUI() {
        getStyleClass().add("bg-image");

        TextField txtTitle = new TextField("Leaderbord");
        txtTitle.getStyleClass().add("Title");
        txtTitle.setEditable(false);
        txtTitle.setFocusTraversable(false);
        txtTitle.setPrefWidth(320);
        txtTitle.setLayoutX(300 - txtTitle.getPrefWidth() / 2);
        txtTitle.setLayoutY(getHeight() / 2 - txtTitle.getHeight() / 2);

        ImageView confetti = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/confetti.gif")));
        confetti.setFitWidth(900);
        confetti.setFitHeight(645);
        confetti.setLayoutX(0);

        Button btnQuit = new Button("Quit");
        btnQuit.setOnAction(this::OnClickBtnQuit);
        btnQuit.getStyleClass().add("button");
        btnQuit.setMinWidth(50);
        btnQuit.setMinHeight(50);
        btnQuit.setLayoutX(525);
        btnQuit.setLayoutY(340);


        rctFirst.setWidth(100);
        rctFirst.setHeight(150);
        rctFirst.setFill(Color.WHITE);

        rctSecond.setWidth(100);
        rctSecond.setHeight(100);
        rctSecond.setFill(Color.WHITE);

        rctThird.setWidth(100);
        rctThird.setHeight(60);
        rctThird.setFill(Color.WHITE);
        rctThird.setStyle("-fx-stroke: gray; -fx-stroke-width: 1;");

        Podium.setMinWidth(rctFirst.getWidth()+rctSecond.getWidth()+rctThird.getWidth() +15);
        Podium.setMinHeight(rctFirst.getWidth());
        Podium.setLayoutX(300 - Podium.getMinWidth() / 2);
        Podium.setLayoutY(225);
        Podium.getChildren().addAll(rctFirst, rctSecond, rctThird);

        rctFirst.setLayoutX(Podium.getLayoutX() + (Podium.getMinWidth() / 3));
        rctFirst.setLayoutY(Podium.getLayoutY()+Podium.getMinHeight()-rctFirst.getHeight());
        rctSecond.setLayoutX(Podium.getLayoutX());
        rctSecond.setLayoutY(Podium.getLayoutY()+Podium.getMinHeight()-rctSecond.getHeight());
        rctThird.setLayoutX(Podium.getLayoutX()+((Podium.getMinWidth() / 3) *2));
        rctThird.setLayoutY(Podium.getLayoutY()+Podium.getMinHeight()-rctThird.getHeight());

        TextField txtFirst = new TextField(dc.determineWinner().get(0).getGebruikersnaam());
        txtFirst.setLayoutX(rctFirst.getLayoutX());
        txtFirst.setLayoutY(rctFirst.getLayoutY() - 30);
        txtFirst.setEditable(false);
        txtFirst.setFocusTraversable(false);
        txtFirst.setPrefWidth(rctFirst.getWidth());
        txtFirst.getStyleClass().add("txt-style");

        TextField txtSecond = new TextField(dc.determineWinner().get(1).getGebruikersnaam());
        txtSecond.setLayoutX(rctSecond.getLayoutX());
        txtSecond.setLayoutY(rctSecond.getLayoutY() - 30);
        txtSecond.setEditable(false);
        txtSecond.setFocusTraversable(false);
        txtSecond.setPrefWidth(rctSecond.getWidth());
        txtSecond.getStyleClass().add("txt-style");

        TextField txtThird = new TextField();
        if(dc.determineWinner().get(2) == null) {
            txtThird.setText("X");
        } else {
            txtThird.setText(dc.determineWinner().get(2).getGebruikersnaam());
        }
        txtThird.setLayoutX(rctThird.getLayoutX());
        txtThird.setLayoutY(rctThird.getLayoutY() - 30);
        txtThird.setEditable(false);
        txtThird.setFocusTraversable(false);
        txtThird.setPrefWidth(rctThird.getWidth());
        txtThird.getStyleClass().add("txt-style");

        ImageView imgGoldTrophy = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/trophyGold.png")));
        imgGoldTrophy.setFitWidth(35);
        imgGoldTrophy.setFitHeight(35);
        imgGoldTrophy.setLayoutX(rctFirst.getLayoutX() + rctFirst.getWidth() / 2 - imgGoldTrophy.getFitWidth() / 2);
        imgGoldTrophy.setLayoutY(rctFirst.getLayoutY() + rctFirst.getHeight() / 2 - imgGoldTrophy.getFitHeight() / 2);

        ImageView imgSilverTrophy = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/trophySilver.png")));
        imgSilverTrophy.setFitWidth(35);
        imgSilverTrophy.setFitHeight(35);
        imgSilverTrophy.setLayoutX(rctSecond.getLayoutX() + rctSecond.getWidth() / 2 - imgSilverTrophy.getFitWidth() / 2);
        imgSilverTrophy.setLayoutY(rctSecond.getLayoutY() + rctSecond.getHeight() / 2 - imgSilverTrophy.getFitHeight() / 2);

        ImageView imgBronzeTrophy = new ImageView(new Image(getClass().getResourceAsStream("/gui/resources/trophyBronze.png")));
        imgBronzeTrophy.setFitWidth(35);
        imgBronzeTrophy.setFitHeight(35);
        imgBronzeTrophy.setLayoutX(rctThird.getLayoutX() + rctThird.getWidth() / 2 - imgBronzeTrophy.getFitWidth() / 2);
        imgBronzeTrophy.setLayoutY(rctThird.getLayoutY() + rctThird.getHeight() / 2 - imgBronzeTrophy.getFitHeight() / 2);

        Button btnDownload = new Button("Download The Leaderboard");
        btnDownload.setOnAction(this::OnClickBtnDownload);
        btnDownload.getStyleClass().add("button");
        btnDownload.setMinWidth(50);
        btnDownload.setMinHeight(50);
        btnDownload.setLayoutX(160);
        btnDownload.setLayoutY(340);


        getChildren().addAll(txtTitle, btnDownload, btnQuit, Podium, rctFirst, rctSecond, rctThird, txtFirst, txtSecond, txtThird, imgGoldTrophy, imgSilverTrophy, imgBronzeTrophy, confetti);

    }

    private void OnClickBtnDownload(ActionEvent event) {
        //Let the user choose the location where to save the picture, then save it by using dc.getLeaderboard(filename)
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Leaderboard");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Downloads"));
        fileChooser.setInitialFileName(dc.getImageName());
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            System.out.println("Saving file to: " + file.getAbsolutePath());
            dc.getLeaderboard(file.getAbsolutePath());

            //Show a confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved");
            alert.setHeaderText("Saved");
            alert.setContentText("The leaderboard has been saved to " + file.getAbsolutePath());
            alert.showAndWait();
        }
    }

    private void OnClickBtnQuit(ActionEvent actionEvent) {
        try {
            dc.clearPlayers();
            StartMenuController startMenu = new StartMenuController(dc);
            Scene scene = new Scene(startMenu, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
