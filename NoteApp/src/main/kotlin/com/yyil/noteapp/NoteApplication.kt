package com.yyil.noteapp

import com.yyil.noteapp.mvc.controller.NoteAreaController
import com.yyil.noteapp.mvc.controller.NoteRepositoryController
import com.yyil.noteapp.mvc.controller.NoteToolBarController
import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteArea
import com.yyil.noteapp.mvc.view.NoteRepository
import com.yyil.noteapp.mvc.view.NoteToolBar
import com.yyil.noteapp.settings.WindowSettings
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage

class NoteApplication : Application() {
    val model = Model()

    lateinit var scene: Scene

    val baseUI = HBox()

    // not the best practice but this probably will never change
    var noteToolBar = NoteToolBar()
    var noteArea = NoteArea(model.tinyMCE.webView)
    val noteRepository = NoteRepository()

    val noteToolBarController = NoteToolBarController(model, noteToolBar, noteRepository)
    var noteAreaController = NoteAreaController(model, noteArea)
    val noteRepositoryController: NoteRepositoryController = NoteRepositoryController(model, noteRepository)

    override fun init() {
        super.init()
    }

    override fun start(stage: Stage) {
        baseUI.children.add(noteToolBar.base)
        baseUI.children.add(noteRepository.base)
        baseUI.children.add(noteArea.base)
        baseUI.fillHeightProperty().set(true)

        scene = Scene(baseUI, WindowSettings.WINDOW_WIDTH, WindowSettings.WINDOW_HEIGHT)
        //scene.stylesheets.add(javaClass.getResource("dark-mode-style.css").toExternalForm());

        stage.scene = scene
        stage.title = WindowSettings.WINDOW_TITLE
        stage.show()
    }
}
