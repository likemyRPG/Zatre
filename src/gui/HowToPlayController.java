package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HowToPlayController extends BorderPane {
    private DomeinController dc;
    private Button btnPrevious;
    private Button btnDone;
    private Button btnNext;
    private Label lblTitel;
    private HBox buttonBox;
    private int paginaTeller = 0;
    private final int MAX_PAGINA = 6;
    private Node[] schermen = new Node[MAX_PAGINA];

    public HowToPlayController(DomeinController dc) {
        try {
            this.dc = dc;
            buildGUI();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void buildGUI() {
        this.genereerSchermen();

        setPadding(new Insets(10));
        getStyleClass().add("bg-image");

        createButtonBox();
        this.setBottom(buttonBox);

        createTitelLabel();
        this.setTop(lblTitel);

        this.setCenter(schermen[0]);
    }

    private void createTitelLabel() {
        lblTitel = new Label("HOW TO PLAY");
        lblTitel.setAlignment(Pos.CENTER);
        lblTitel.setMinWidth(200);
        lblTitel.setMaxWidth(Double.MAX_VALUE);
        lblTitel.setFont(new Font(30));
        lblTitel.setStyle("-fx-text-fill: white");
    }

    private void createDoneButton() {
        btnDone = new Button("Done");
        btnDone.setMaxWidth(Double.MAX_VALUE);
        btnDone.setMinWidth(200);
        btnDone.setOnAction(this::doneButtonOnAction);
        btnDone.setLayoutX(230);
        btnDone.setLayoutY(200);

    }

    private void createNextButon() {
        btnNext = new Button("->");
        btnNext.setMaxWidth(100);
        btnNext.setMinWidth(100);
        btnNext.setOnAction(this::nextButtonOnAction);
    }

    private void createPreviousButton() {
        btnPrevious = new Button("<-");
        btnPrevious.setMaxWidth(100);
        btnPrevious.setMinWidth(100);
        btnPrevious.setDisable(true);
        btnPrevious.setStyle("-fx-background-color: transparent;");
        btnPrevious.setOnAction(this::previousButtonOnAction);

    }

    private void createButtonBox() {
        createDoneButton();
        createPreviousButton();
        createNextButon();
        buttonBox = new HBox(8);
        buttonBox.getChildren().addAll(btnPrevious, btnDone, btnNext);
        buttonBox.setAlignment(Pos.CENTER);
    }

    private void previousButtonOnAction(ActionEvent event) {
        paginaTeller--;
        if (paginaTeller == MAX_PAGINA - 2) {
            btnNext.setDisable(false);
            btnNext.setStyle(btnPrevious.getStyle());
        }
        if (paginaTeller - 1 == -1) {
            btnPrevious.setDisable(true);
            btnPrevious.setStyle("-fx-background-color: transparent;");
        }
        this.setCenter(schermen[paginaTeller]);
    }

    private void doneButtonOnAction(ActionEvent event) {
        try {
            StartMenuController StartMenu = new StartMenuController(dc);
            Scene scene = new Scene(StartMenu, 900, 645);
            scene.getStylesheets().add(getClass().getResource("/gui/resources/style.css").toExternalForm());
            Stage stage = (Stage) this.getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void nextButtonOnAction(ActionEvent event) {
        paginaTeller++;
        if (paginaTeller == 1) {
            btnPrevious.setDisable(false);
            btnPrevious.setStyle(btnNext.getStyle());
        }
        if (paginaTeller + 1 == MAX_PAGINA) {
            btnNext.setDisable(true);
            btnNext.setStyle("-fx-background-color: transparent;");
        }
        this.setCenter(schermen[paginaTeller]);
    }

    private void genereerSchermen() {
        schermen[0] = createScherm1();
        schermen[1] = createScherm2();
        schermen[2] = createScherm3();
        schermen[3] = createScherm4();
        schermen[4] = createScherm5();
        schermen[5] = createScherm6();
    }

    private Pane createScherm1() {
        Pane scherm = new Pane();
        scherm.setStyle("-fx-background-color: red;");
        return scherm;
    }

    private Pane createScherm2() {
        Pane scherm = new Pane();
        scherm.setStyle("-fx-background-color: green;");
        return scherm;
    }

    private Pane createScherm3() {
        Pane scherm = new Pane();
        scherm.setStyle("-fx-background-color: blue;");
        return scherm;
    }

    private Pane createScherm4() {
        Pane scherm = new Pane();
        scherm.setStyle("-fx-background-color: orange;");
        return scherm;
    }

    private Pane createScherm5() {
        Pane scherm = new Pane();
        scherm.setStyle("-fx-background-color: yellow;");
        return scherm;
    }

    private BorderPane createScherm6() {
        BorderPane scherm = new BorderPane();
        scherm.setStyle("-fx-background-color: pink;");
        Button testButton = new Button("Finish");
        testButton.setMaxWidth(Double.MAX_VALUE);
        testButton.setMinWidth(200);
        scherm.setCenter(testButton);
        return scherm;
    }


}




