package com.yyil.noteapp

import com.yyil.noteapp.mvc.controller.NoteRepositoryController
import com.yyil.noteapp.settings.WindowSettings
import com.yyil.noteapp.mvc.view.NoteArea
import com.yyil.noteapp.mvc.view.NoteRepository
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage

class NoteApplication : Application() {
    val tinyMCEInterface = TinyMCEInterface("TESTING INIT CONTENT")

    val baseUI = HBox()
    var noteArea = NoteArea(tinyMCEInterface)
    val noteRepository = NoteRepository()

    val noteRepositoryController: NoteRepositoryController = NoteRepositoryController(noteRepository)

    override fun init() {
        super.init()
        // If we want to initialize objects (or connections) prior to starting the app display up
        // Could be useful for loading in notes
    }

    override fun start(stage: Stage) {
        baseUI.children.add(noteRepository.base)
        baseUI.children.add(noteArea.base)
        baseUI.fillHeightProperty().set(true)

        noteRepositoryController.handleEvents()

        noteRepository.noteList.onMouseClicked = EventHandler { _ ->
            val i = noteRepository.noteList.selectionModel.selectedIndex
            val tempContent = FXCollections.observableArrayList(
                "You have opened Note1!", "Note2 Lorem Ipsum", "Note3 Huak Huak Huak", "Note4 READING WEAEK SOON"
            )
            println(tinyMCEInterface.selection)
            tinyMCEInterface.selection = tempContent[i]
        }

        stage.scene = Scene(baseUI, WindowSettings.WINDOW_WIDTH, WindowSettings.WINDOW_HEIGHT)
        stage.title = WindowSettings.WINDOW_TITLE
        stage.show()
    }
}
