package com.yyil.noteapp

import com.yyil.noteapp.constant.ComponentConstant
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
import javafx.scene.image.Image
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

    val noteToolBarController = NoteToolBarController(
        model, noteToolBar, noteRepository
    )
    var noteAreaController = NoteAreaController(model, noteArea)
    val noteRepositoryController: NoteRepositoryController = NoteRepositoryController(model, noteRepository)

    lateinit var defaultStyle: String
    lateinit var darkModeStyle: String

    override fun init() {
        super.init()
    }

    override fun start(stage: Stage) {
        defaultStyle = javaClass.getResource(ComponentConstant.DEFAULT_CSS).toExternalForm()
        darkModeStyle = javaClass.getResource(ComponentConstant.DARK_MODE_CSS).toExternalForm()

        baseUI.children.add(noteToolBar.base)
        baseUI.children.add(noteRepository.base)
        baseUI.children.add(noteArea.base)
        baseUI.fillHeightProperty().set(true)

        scene = Scene(baseUI, WindowSettings.WINDOW_WIDTH, WindowSettings.WINDOW_HEIGHT)
        scene.stylesheets.add(defaultStyle)

        noteToolBarController.handleSwitchTheme(scene, noteToolBar, defaultStyle, darkModeStyle)

        stage.minWidth = ComponentConstant.STAGE_MIN_WIDTH
        stage.scene = scene
        stage.title = WindowSettings.WINDOW_TITLE
        stage.icons.add(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.TITLE_BAR_ICON_PATH).toString()
            )
        )
        stage.show()
    }
}
