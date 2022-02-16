module com.yyil.noteapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires javafx.base;

    // Json
    requires kotlinx.serialization.core;
    requires kotlinx.serialization.json;

    opens com.yyil.noteapp to javafx.fxml;
    exports com.yyil.noteapp;

    // Json
    exports com.yyil.noteapp.entity;
}
