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

    // JSON
    requires kotlinx.serialization.core;
    requires kotlinx.serialization.json;

    requires jdk.jsobject;

    opens com.yyil.noteapp to javafx.fxml;
    exports com.yyil.noteapp;

    // JSON
    exports com.yyil.noteapp.entity;
}
