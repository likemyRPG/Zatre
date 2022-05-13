package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Calendar;

import static persistence.language.rb;

public class RegisterController extends GridPane {

    //Variables
    private DomeinController dc;
    //Elements
    private Label usernameLabel;
    private Label birthyearLabel;
    private TextField usernameTextField;
    private Label messageLabel;
    private Button registerButton;
    private Button cancelButton;
    ComboBox<Integer> birthyearComboBox;

    public RegisterController(DomeinController dc) {
        try{
            this.dc = dc;
            buildGUI();
            addBirthyearToComboBox();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void buildGUI() {
        setVgap(20);
        setHgap(10);
        setPadding(new Insets(10));
        getStyleClass().add("bg-image");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        col1.setPercentWidth(15);
        col2.setPercentWidth(30);
        col3.setPercentWidth(20);
        col4.setPercentWidth(35);
        getColumnConstraints().addAll(col1, col2, col3, col4);

        registerButton = new Button(rb.getString("register"));
        add(registerButton, 1,11);
        registerButton.setMaxWidth(240);

        GridPane.setColumnSpan(registerButton, 2);
        registerButton.setOnAction(this::registerButtonOnAction);

        cancelButton = new Button(rb.getString("back"));
        cancelButton.setMaxWidth(240);
        add(cancelButton, 1,12);
        GridPane.setColumnSpan(cancelButton, 2);
        cancelButton.setOnAction(this::cancelButtonOnAction);

        usernameLabel = new Label(rb.getString("fillInUsername"));
        usernameLabel.getStyleClass().add("lblText");
        usernameLabel.setTextAlignment(TextAlignment.LEFT);
        add(usernameLabel, 1, 4);
        GridPane.setColumnSpan(usernameLabel, 2);

        birthyearLabel = new Label(rb.getString("fillInBirthYear"));
        birthyearLabel.getStyleClass().add("lblText");
        birthyearLabel.setAlignment(Pos.BOTTOM_RIGHT);
        add(birthyearLabel, 1, 7);
        GridPane.setColumnSpan(birthyearLabel, 2);

        birthyearComboBox = new ComboBox<>();
        birthyearComboBox.setMaxWidth(Double.MAX_VALUE);
        add(birthyearComboBox, 1, 8);
        GridPane.setColumnSpan(birthyearComboBox, 2);

        usernameTextField = new TextField();
        usernameTextField.setMaxWidth(Double.MAX_VALUE);
        add(usernameTextField, 1, 5);
        GridPane.setColumnSpan(usernameTextField, 2);
        usernameTextField.setPromptText(rb.getString("username"));

        messageLabel = new Label();
        messageLabel.setMaxWidth(Double.MAX_VALUE);
        messageLabel.getStyleClass().add("lblLogin");
        messageLabel.setAlignment(Pos.BASELINE_LEFT);
        add(messageLabel, 1, 9);
        GridPane.setColumnSpan(messageLabel, 2);
    }

    private void addBirthyearToComboBox() {
        Calendar cal = Calendar.getInstance();
        for(int i = cal.get(Calendar.YEAR)-6; i >= 1900; i--) {
            birthyearComboBox.getItems().add(i);
        }
    }

    public void registerButtonOnAction(ActionEvent event) {
        try{
            System.out.println(usernameTextField.getText() + birthyearComboBox.getValue());
            dc.checkRegister(usernameTextField.getText(), birthyearComboBox.getValue());
            AddPlayersOptionController AddPlayersOption = new AddPlayersOptionController(dc);
            Scene scene = new Scene(AddPlayersOption, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (NullPointerException e) {
            messageLabel.setText(rb.getString("Username&Birthday"));
        }catch (Exception e) {
            messageLabel.setText(e.getMessage());
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        try {
            AddPlayersOptionController AddPlayer = new AddPlayersOptionController(dc);
            Scene scene = new Scene(AddPlayer, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
