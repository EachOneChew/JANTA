package com.yyil.noteapp

import com.yyil.noteapp.settings.WindowSettings
import com.yyil.noteapp.ui.NoteBaseUI
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class NoteApplication : Application() {

    override fun init() {
        super.init()
        // If we want to initialize objects (or connections) prior to starting the app display up
        // Could be useful for loading in notes
    }

    override fun start(stage: Stage) {
        NoteBaseUI.init()
        stage.scene = Scene(NoteBaseUI.base, WindowSettings.WINDOW_WIDTH, WindowSettings.WINDOW_HEIGHT)
        stage.title = WindowSettings.WINDOW_TITLE
        stage.show()
    }
}
