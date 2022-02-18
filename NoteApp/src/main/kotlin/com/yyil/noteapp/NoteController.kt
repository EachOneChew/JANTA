package com.yyil.noteapp

import javafx.fxml.FXML
import javafx.scene.control.Label

class NoteController {
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"
    }
}