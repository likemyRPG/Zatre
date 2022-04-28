package gui;

import domein.DomeinController;
import exceptions.OutOfRangeException;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Text;

import java.util.Calendar;

import static persistence.language.rb;

public class RegisterController extends GridPane {

    //Variables
    private DomeinController dc;
    //Elements
    private Label usernameLabel;
    private Label birthyearLabel;
    private TextField usernameTextField;
    private TextField birthyearTextField;
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
        getStyleClass().add("bg-style");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col1, col2);

        registerButton = new Button(rb.getString("register"));
        registerButton.setMaxWidth(Double.MAX_VALUE);
        add(registerButton, 0,5);
        GridPane.setColumnSpan(registerButton, 2);
        registerButton.setOnAction(this::registerButtonOnAction);

        cancelButton = new Button(rb.getString("back"));
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        add(cancelButton, 0,6);
        GridPane.setColumnSpan(cancelButton, 2);
        cancelButton.setOnAction(this::cancelButtonOnAction);

        usernameLabel = new Label(rb.getString("fillInUsername"));
        usernameLabel.setMaxWidth(Double.MAX_VALUE);
        usernameLabel.getStyleClass().add("lblText");
        add(usernameLabel, 0, 2);

        birthyearLabel = new Label(rb.getString("fillInBirthYear"));
        birthyearLabel.setMaxWidth(Double.MAX_VALUE);
        birthyearLabel.getStyleClass().add("lblText");
        add(birthyearLabel, 0, 3);

        //Let the user choose their birthyear by a combobox
        birthyearComboBox = new ComboBox<>();
        birthyearComboBox.setMaxWidth(Double.MAX_VALUE);
        add(birthyearComboBox, 1, 3);

        usernameTextField = new TextField();
        usernameTextField.setMaxWidth(Double.MAX_VALUE);
        add(usernameTextField, 1, 2);
        usernameTextField.setPromptText(rb.getString("username"));

        messageLabel = new Label();
        messageLabel.setMaxWidth(Double.MAX_VALUE);
        messageLabel.getStyleClass().add("lblLogin");
        add(messageLabel, 0, 4);
        GridPane.setColumnSpan(messageLabel, 2);
    }

    private void addBirthyearToComboBox() {
        Calendar cal = Calendar.getInstance();
        for(int i = cal.get(Calendar.YEAR - 6); i >= 1900; i--) {
            birthyearComboBox.getItems().add(i);
        }
    }

    public void registerButtonOnAction(ActionEvent event) {
        try{
            dc.checkRegister(usernameLabel.getText(), birthyearComboBox.getValue());
            PlayerInformationController PlayerInformation = new PlayerInformationController(dc);
            Scene scene = new Scene(PlayerInformation, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (IllegalArgumentException e){
            messageLabel.setText("Fill in all the requirements!");
        } catch (OutOfRangeException e) {
            messageLabel.setText("String.valueOf(e)");
        }catch (NullPointerException e){
            messageLabel.setText("Choose your birthdate");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        try {
            AddPlayersOptionController AddPlayer = new AddPlayersOptionController(dc);
            Scene scene = new Scene(AddPlayer, 600, 400);
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
