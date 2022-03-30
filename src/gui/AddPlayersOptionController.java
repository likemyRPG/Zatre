package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import static persistence.language.rb;

public class AddPlayersOptionController extends GridPane {

    private DomeinController dc;

    Button btnReturn;
    Button btnLogin;
    Button btnRegister;

    public AddPlayersOptionController(DomeinController dc){
        try
        {
            this.dc = dc;
            buildGUI();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void buildGUI() {
        setVgap(20);
        setHgap(10);
        setPadding(new Insets(10));
        getStyleClass().add("bg-style");

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col1, col2);

        btnRegister = new Button(rb.getString("register"));
        btnRegister.setMaxWidth(Double.MAX_VALUE);
        add(btnRegister, 1,3);
        btnRegister.setOnAction(this::onClickRegister);

        btnLogin = new Button(rb.getString("login"));
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        add(btnLogin, 1,4);
        btnLogin.setOnAction(this::onClickLogin);

        btnReturn = new Button(rb.getString("back"));
        btnReturn.setMaxWidth(Double.MAX_VALUE);
        add(btnReturn, 1,5);
        btnReturn.setOnAction(this::onClickReturn);
    }

    private void onClickRegister(ActionEvent event) {
    }

    private void onClickLogin(ActionEvent event) {
        try {
            LoginController login = new LoginController(dc); // <1>
            Scene scene = new Scene(login, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private void onClickReturn(ActionEvent event) {
        try {
            StartMenuController StartMenu = new StartMenuController(dc); // <1>
            Scene scene = new Scene(StartMenu, 600, 400);
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
