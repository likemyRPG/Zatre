package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PlayerInformationController extends Pane {
    private DomeinController dc;
    private Button btnOk;
    private Label lblInformation;

    public PlayerInformationController(DomeinController dc){
        try {
            this.dc = dc;
            buildGUI();
        }catch  (Exception e){
            System.out.println(e);
        }
    }

    private void buildGUI() {
        setPadding(new Insets(10));
        getStyleClass().add("bg-style");

        btnOk = new Button("Ok");
        btnOk.setMaxWidth(Double.MAX_VALUE);
        btnOk.setMinWidth(200);
        btnOk.setOnAction(this::onClickButtonOk);

        btnOk.setLayoutY(200);
        btnOk.setLayoutX(200);

        lblInformation = new Label(dc.geefOverzicht());
        lblInformation.setMaxWidth(500);
        lblInformation.setWrapText(true);
        lblInformation.setTextAlignment(TextAlignment.CENTER);
        lblInformation.getStyleClass().add("lblText");

        lblInformation.setLayoutY(100);
        lblInformation.setLayoutX(50);

        this.getChildren().addAll(btnOk, lblInformation);
    }

    private void onClickButtonOk(ActionEvent event) {
        try {
            AddPlayersOptionController AddPlayer = new AddPlayersOptionController(dc);
            Scene scene = new Scene(AddPlayer, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
