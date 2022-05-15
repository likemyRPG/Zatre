package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.awt.*;
import java.nio.channels.ShutdownChannelGroupException;
import static persistence.language.rb;

public class HowToPlayController extends BorderPane {
    private DomeinController dc;
    private Button btnPrevious;
    private Button btnDone;
    private Button btnNext;
    private Label lblTitel;
    private HBox buttonBox;
    private int paginaTeller = 0;
    private final int MAX_PAGINA = 5;
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
        lblTitel = new Label(rb.getString("lblHowToPlay"));
        lblTitel.setAlignment(Pos.CENTER);
        lblTitel.setMinWidth(200);
        lblTitel.setMaxWidth(Double.MAX_VALUE);
        lblTitel.setMinHeight(70);
        lblTitel.setFont(new Font(40));
        lblTitel.setStyle("-fx-text-fill: white");
    }

    private void createDoneButton() {
        btnDone = new Button(rb.getString("btndone"));
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
        buttonBox.setMinHeight(50);
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
        schermen[0] = createUitlegText1();
        schermen[1] = createUitlegText2();
        schermen[2] = createUitlegText3();
        schermen[3] = createUitlegText4();
        schermen[4] = createUitlegText5();
    }

    private VBox createVbox1() {
        VBox vbox1 = new VBox();
        Label lblregistreren = new Label(rb.getString("lblRegistreren"));

        lblregistreren.setFont(new Font(25));
        lblregistreren.setStyle("-fx-text-fill: white");
        lblregistreren.setMaxWidth(Double.MAX_VALUE);
        lblregistreren.setMinWidth(200);

        vbox1.getChildren().add(lblregistreren);

        return vbox1;
    }

    private VBox createUitlegText1() {
        VBox vbox1 = new VBox();
        HBox lblbox = new HBox();
        Label lbluitlegtext = new Label();
        ImageView img = new ImageView(rb.getString("voorbeeld_registreren"));
        HBox imgBox = new HBox();
        Label gap0 = new Label();
        Label gap1 = new Label();
        Label gap2 = new Label();

        gap0.setMinHeight(20);
        gap1.setMinHeight(20);
        gap2.setMinHeight(50);

        lbluitlegtext.setText(rb.getString("uitlegtextRegistreren"));
        lbluitlegtext.setWrapText(true);
        lbluitlegtext.getStyleClass().add("uitlegtext-style");
        lbluitlegtext.setTextAlignment(TextAlignment.CENTER);
        lbluitlegtext.setMaxWidth(500);

        lblbox.getChildren().add(lbluitlegtext);
        lblbox.setAlignment(Pos.CENTER);

        img.setPreserveRatio(true);
        img.setFitWidth(450);
        img.getStyleClass().add("imageuitleg-style");

        imgBox.getChildren().add(img);
        imgBox.setAlignment(Pos.CENTER);

        vbox1.getChildren().addAll(gap0,createVbox1(),gap1,lblbox,gap2,imgBox);
        return vbox1;
    }

    private VBox createVbox2() {
        VBox vbox2 = new VBox();
        Label lblinloggen = new Label(rb.getString("lblAanmelden"));

        lblinloggen.setFont(new Font(25));
        lblinloggen.setStyle("-fx-text-fill: white");
        lblinloggen.setMaxWidth(Double.MAX_VALUE);
        lblinloggen.setMinWidth(200);

        vbox2.getChildren().add(lblinloggen);
        return vbox2;
    }

    private VBox createUitlegText2() {
        VBox vbox1 = new VBox();
        HBox lblbox = new HBox();
        Label lbluitlegtext = new Label();
        ImageView img = new ImageView(rb.getString("voorbeeld_inloggen"));
        HBox imgBox = new HBox();
        Label gap0 = new Label();
        Label gap1 = new Label();
        Label gap2 = new Label();

        gap0.setMinHeight(20);
        gap1.setMinHeight(20);
        gap2.setMinHeight(50);

        lbluitlegtext.setText(rb.getString("uitlegtextInloggen"));
        lbluitlegtext.setWrapText(true);
        lbluitlegtext.getStyleClass().add("uitlegtext-style");
        lbluitlegtext.setTextAlignment(TextAlignment.CENTER);
        lbluitlegtext.setMaxWidth(500);

        lblbox.getChildren().add(lbluitlegtext);
        lblbox.setAlignment(Pos.CENTER);

        img.setPreserveRatio(true);
        img.setFitWidth(400);
        img.getStyleClass().add("imageuitleg-style");

        imgBox.getChildren().add(img);
        imgBox.setAlignment(Pos.CENTER);

        vbox1.getChildren().addAll(gap0,createVbox2(),gap1,lblbox,gap2,imgBox);
        return vbox1;
    }

    private VBox createVbox3() {
        VBox vbox3 = new VBox();
        Label lblSpelers = new Label(rb.getString("lblSpelers"));

        lblSpelers.setFont(new Font(25));
        lblSpelers.setStyle("-fx-text-fill: white");
        lblSpelers.setMaxWidth(Double.MAX_VALUE);
        lblSpelers.setMinWidth(200);

        vbox3.getChildren().add(lblSpelers);

        return vbox3;
    }

    private VBox createUitlegText3() {
        VBox vbox1 = new VBox();
        HBox lblbox = new HBox();
        Label lbluitlegtext = new Label();
        ImageView img = new ImageView(rb.getString("voorbeeld_spelers"));
        HBox imgBox = new HBox();
        Label gap0 = new Label();
        Label gap1 = new Label();
        Label gap2 = new Label();

        gap0.setMinHeight(20);
        gap1.setMinHeight(20);
        gap2.setMinHeight(50);

        lbluitlegtext.setText(rb.getString("uitlegtextSpelers"));
        lbluitlegtext.setWrapText(true);
        lbluitlegtext.getStyleClass().add("uitlegtext-style");
        lbluitlegtext.setTextAlignment(TextAlignment.CENTER);
        lbluitlegtext.setMaxWidth(500);

        lblbox.getChildren().add(lbluitlegtext);
        lblbox.setAlignment(Pos.CENTER);

        img.setPreserveRatio(true);
        img.setFitWidth(450);
        img.getStyleClass().add("imageuitleg-style");

        imgBox.getChildren().add(img);
        imgBox.setAlignment(Pos.CENTER);

        vbox1.getChildren().addAll(gap0,createVbox3(),gap1,lblbox,gap2,imgBox);
        return vbox1;
    }

    private VBox createVbox4() {
        VBox vbox1 = new VBox();
        Label lblSpelBord = new Label(rb.getString("lblSpelBord"));

        lblSpelBord.setFont(new Font(25));
        lblSpelBord.setStyle("-fx-text-fill: white");
        lblSpelBord.setMaxWidth(Double.MAX_VALUE);
        lblSpelBord.setMinWidth(200);

        vbox1.getChildren().add(lblSpelBord);

        return vbox1;
    }

    private VBox createUitlegText4() {
        VBox vbox1 = new VBox();
        HBox lblbox = new HBox();
        Label lbluitlegtext = new Label();
        ImageView img = new ImageView(rb.getString("voorbeeld_spelbord"));
        HBox imgBox = new HBox();
        Label gap0 = new Label();
        Label gap1 = new Label();
        Label gap2 = new Label();

        gap0.setMinHeight(20);
        gap1.setMinHeight(20);
        gap2.setMinHeight(50);

        lbluitlegtext.setText(rb.getString("uitlegtextSpelBord"));
        lbluitlegtext.setWrapText(true);
        lbluitlegtext.getStyleClass().add("uitlegtext-style");
        lbluitlegtext.setTextAlignment(TextAlignment.CENTER);
        lbluitlegtext.setMaxWidth(500);

        lblbox.getChildren().add(lbluitlegtext);
        lblbox.setAlignment(Pos.CENTER);

        img.setPreserveRatio(true);
        img.setFitWidth(250);
        img.getStyleClass().add("imageuitleg-style");

        imgBox.getChildren().add(img);
        imgBox.setAlignment(Pos.CENTER);

        vbox1.getChildren().addAll(gap0,createVbox4(),gap1,lblbox,gap2,imgBox);
        return vbox1;
    }

    private VBox createVbox5() {
        VBox vbox1 = new VBox();
        Label lblSpelRegels = new Label(rb.getString("lblSpelRegels"));

        lblSpelRegels.setFont(new Font(25));
        lblSpelRegels.setStyle("-fx-text-fill: white");
        lblSpelRegels.setMaxWidth(Double.MAX_VALUE);
        lblSpelRegels.setMinWidth(200);

        vbox1.getChildren().add(lblSpelRegels);

        return vbox1;
    }

    private VBox createUitlegText5() {
        VBox vbox1 = new VBox();
        HBox lblbox = new HBox();
        Label lbluitlegtext = new Label();
        ImageView img = new ImageView(rb.getString("voorbeeld_spelregels"));
        HBox imgBox = new HBox();
        Label gap0 = new Label();
        Label gap1 = new Label();
        Label gap2 = new Label();

        gap0.setMinHeight(20);
        gap1.setMinHeight(20);
        gap2.setMinHeight(20);

        lbluitlegtext.setText(rb.getString("uitlegtextSpelRegels"));
        lbluitlegtext.setWrapText(true);
        lbluitlegtext.getStyleClass().add("uitlegtext-style");
        lbluitlegtext.setTextAlignment(TextAlignment.CENTER);
        lbluitlegtext.setMaxWidth(500);

        lblbox.getChildren().add(lbluitlegtext);
        lblbox.setAlignment(Pos.CENTER);

        img.setPreserveRatio(true);
        img.setFitWidth(380);
        img.getStyleClass().add("imageuitleg-style");

        imgBox.getChildren().add(img);
        imgBox.setAlignment(Pos.CENTER);

        vbox1.getChildren().addAll(gap0,createVbox5(),gap1,lblbox,gap2,imgBox);
        return vbox1;
    }

}




