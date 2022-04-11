package main;

import domein.DomeinController;
import gui.GameBoardController;
import gui.StartScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        DomeinController dc = new DomeinController();

        StartScreenController root = new StartScreenController(dc);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Zatre");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}