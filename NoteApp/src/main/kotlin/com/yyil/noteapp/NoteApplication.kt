package com.yyil.noteapp

import com.yyil.noteapp.constant.ComponentConstant
import com.yyil.noteapp.mvc.controller.LabelListController
import com.yyil.noteapp.mvc.controller.NoteAreaController
import com.yyil.noteapp.mvc.controller.NoteRepositoryController
import com.yyil.noteapp.mvc.controller.NoteToolBarController
import com.yyil.noteapp.mvc.model.Connect
import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.LabelList
import com.yyil.noteapp.mvc.view.NoteArea
import com.yyil.noteapp.mvc.view.NoteRepository
import com.yyil.noteapp.mvc.view.NoteToolBar
import com.yyil.noteapp.settings.WindowSettings
import com.yyil.noteapp.svg.BufferedImageTranscoder
import javafx.application.Application
import javafx.embed.swing.SwingFXUtils
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import javafx.stage.Stage
import org.apache.batik.transcoder.TranscoderInput


class NoteApplication : Application() {
    val model = Model()

    lateinit var scene: Scene

    val baseUI = HBox()

    // not the best practice but this probably will never change
    var noteToolBar = NoteToolBar()
    var noteArea = NoteArea(model.tinyMCE.webView)
    val noteRepository = NoteRepository()
    val labelList = LabelList()

    val noteToolBarController = NoteToolBarController(
        model, noteToolBar, noteRepository, labelList
    )
    var noteAreaController = NoteAreaController(model, noteArea)
    val noteRepositoryController = NoteRepositoryController(model, noteRepository)
    val labelListController = LabelListController(model, labelList)

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
        baseUI.children.add(labelList.base)
        baseUI.children.add(noteArea.base)
        baseUI.fillHeightProperty().set(true)

        scene = Scene(baseUI, WindowSettings.WINDOW_WIDTH, WindowSettings.WINDOW_HEIGHT)
        scene.stylesheets.add(defaultStyle)

        noteToolBarController.handleSwitchTheme(scene, noteToolBar, defaultStyle, darkModeStyle)

        stage.minWidth = ComponentConstant.STAGE_MIN_WIDTH
        stage.scene = scene
        stage.title = WindowSettings.WINDOW_TITLE
        stage.icons.add(
            BufferedImageTranscoder.transcodeToImage(
                NoteApplication::class.java.getResource(ComponentConstant.TITLE_BAR_ICON_PATH).toString()
            )
        )
        stage.show()
    }

    override fun stop() {
        super.stop()
        Connect.close(Connect.getConnection())
    }
}
