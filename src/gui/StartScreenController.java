package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//The first screen that the user sees when the game starts.
public class StartScreenController extends Pane {
    private DomeinController dc;
    private Button btnStartProgram;
    private Button btnQuit;

    public StartScreenController(DomeinController dc) {
        try
        {
            this.dc = dc;
            buildGUI();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void buildGUI() {
        setPadding(new Insets(10));
        getStyleClass().add("bg-style");

        ImageView frontImage = new ImageView(new Image(
                getClass().getResourceAsStream
                        ("/gui/resources/Zatre3.png")));
        frontImage.getStyleClass().add("frontImage");
        GridPane.setColumnSpan(frontImage, 2);

        frontImage.setLayoutX(110);
        frontImage.setLayoutY(50);

        btnStartProgram = new Button("Start");
        btnStartProgram.setMaxWidth(Double.MAX_VALUE);
        btnStartProgram.setOnAction(this::onClickButtonStartProgram);

        btnStartProgram.setLayoutX(200);
        btnStartProgram.setLayoutY(210);

        btnStartProgram.setMinWidth(180);
        btnStartProgram.setMinHeight(80);

        btnQuit = new Button("Quit");
        btnQuit.setMaxWidth(Double.MAX_VALUE);
        btnQuit.setOnAction(this::onClickButtonQuit);

        btnQuit.setLayoutY(340);
        btnQuit.setLayoutX(14);

        this.getChildren().addAll(btnQuit, btnStartProgram, frontImage);
    }

    public void onClickButtonStartProgram(ActionEvent event){
        try
        {
            LanguageSelectionController language = new LanguageSelectionController(dc); // <1>
            Scene scene = new Scene(language, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void onClickButtonQuit(ActionEvent event){
        try
        {
            Stage stage = (Stage) btnQuit.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
