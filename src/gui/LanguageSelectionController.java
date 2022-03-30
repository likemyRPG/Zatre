package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import main.Main;
import persistence.language;

import java.util.ResourceBundle;

public class LanguageSelectionController extends GridPane {
    private DomeinController dc;
    private ComboBox languageComboBox;
    private Button btnContinue;
    language ln = new language();
    ResourceBundle rb;
    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Nederlands",
                    "English",
                    "Français"
            );

    public LanguageSelectionController(DomeinController dc) {
        try{
            this.dc = dc;
            buildGui();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void buildGui() {
        setVgap(20);
        setHgap(10);
        setPadding(new Insets(10));
        getStyleClass().add("bg-style");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col1, col2);

        languageComboBox = new ComboBox(options);
        languageComboBox.setMaxWidth(Double.MAX_VALUE);
        languageComboBox.getSelectionModel().selectFirst();
        languageComboBox.getStyleClass().add("combo-box");
        add(languageComboBox, 1, 4);

        btnContinue = new Button("Select");
        btnContinue.setMaxWidth(Double.MAX_VALUE);
        add(btnContinue, 1,5);
        btnContinue.setOnAction(this::onClickContinue);
    }

    public void onClickContinue(ActionEvent event) {
        try
        {
            language ln = new language();
            String language = (String) languageComboBox.getValue();
            switch (language){
                case "Nederlands": language = "nl";
                break;
                case "English": language = "en";
                break;
                case "Français": language = "fr";
                break;
                default:language = "nl";
            }
            ln.setGekozenTaal(language);
            ln.taal();
            StartMenuController startMenu = new StartMenuController(dc);
            Scene scene = new Scene(startMenu, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
