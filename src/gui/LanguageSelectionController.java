package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistence.language;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LanguageSelectionController extends GridPane {
    private DomeinController dc;
    private ComboBox languageComboBox;
    private Button btnContinue;
    private Label lblLanguage;

    language ln = new language();
    ResourceBundle rb;
    HashMap<String, String> languageShort;
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
        getStyleClass().add("bg-image");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        col1.setPercentWidth(8);
        col2.setPercentWidth(54);
        col3.setPercentWidth(38);
        getColumnConstraints().addAll(col1, col2, col3);

        languageComboBox = new ComboBox(options);
        languageComboBox.setMaxWidth(Double.MAX_VALUE);
        languageComboBox.getSelectionModel().selectFirst();
        languageComboBox.getStyleClass().add("combo-box");
        languageComboBox.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        languageComboBox.setStyle("-fx-background-color: transparent;");
        languageComboBox.setStyle("-fx-border-color: transparent;");
        add(languageComboBox, 1, 10);

        languageComboBox.setLayoutX(250);
        languageComboBox.setLayoutY(250);

        lblLanguage = new Label();
        lblLanguage.setText("Select your language");
        lblLanguage.getStyleClass().add("Title");
        add(lblLanguage, 1, 5);
        GridPane.setColumnSpan(lblLanguage, 1);

        btnContinue = new Button("Select");
        btnContinue.setMaxWidth(Double.MAX_VALUE);
        add(btnContinue, 1,13);
        btnContinue.setOnAction(this::onClickContinue);
    }

    public void onClickContinue(ActionEvent event) {
        try
        {
            language ln = new language();
            String language = (String) languageComboBox.getValue();
            languageShort = new HashMap<>();
            hashMapAddValue();
            ln.setGekozenTaal(languageShort.get(language));
            ln.taal();
            StartMenuController startMenu = new StartMenuController(dc);
            Scene scene = new Scene(startMenu, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void hashMapAddValue() {
        languageShort.put("Nederlands", "nl");
        languageShort.put("English", "en");
        languageShort.put("Français", "fr");
    }
}
