package com.yyil.noteapp

import com.yyil.noteapp.controller.NoteRepositoryController
import com.yyil.noteapp.settings.WindowSettings
import com.yyil.noteapp.ui.NoteArea
import com.yyil.noteapp.ui.NoteRepository
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage

class NoteApplication : Application() {
    val tinyMCEInterface = TinyMCEInterface("TESTING INIT CONTENT")

    val baseUI = HBox()
    var noteArea = NoteArea(tinyMCEInterface)
    val noteRepository = NoteRepository()

    lateinit var noteRepositoryController: NoteRepositoryController

    override fun init() {
        super.init()
        // If we want to initialize objects (or connections) prior to starting the app display up
        // Could be useful for loading in notes
    }

    override fun start(stage: Stage) {
        baseUI.children.add(noteRepository.base)
        baseUI.children.add(noteArea.base)
        baseUI.fillHeightProperty().set(true)

        noteRepositoryController = NoteRepositoryController(noteRepository)
        noteRepositoryController.handleEvents()

        stage.scene = Scene(baseUI, WindowSettings.WINDOW_WIDTH, WindowSettings.WINDOW_HEIGHT)
        stage.title = WindowSettings.WINDOW_TITLE
        stage.show()
    }
}
