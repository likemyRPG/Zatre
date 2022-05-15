module gui {
    exports gui;
    exports main;

    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;

    opens gui to javafx.graphics, javafx.fxml;
    opens main to javafx.graphics, javafx.fxml;
}