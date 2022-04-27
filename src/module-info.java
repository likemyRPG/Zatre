module gui {
    exports gui;
    exports main;

    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.fxml;

    requires javafx.media;
    requires org.junit.jupiter.params;


    opens gui to javafx.graphics, javafx.fxml;
    opens main to javafx.graphics, javafx.fxml;
}
