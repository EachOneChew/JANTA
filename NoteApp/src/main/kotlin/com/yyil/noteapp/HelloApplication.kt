package com.yyil.noteapp

import com.yyil.noteapp.settings.WindowSettings
import com.yyil.noteapp.ui.UiComponent
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


class HelloApplication : Application() {

    override fun init() {
        super.init()
        // If we want to initialize objects (or connections) prior to starting the app display up
        // Could be useful for loading in notes
    }

    override fun start(stage: Stage) {
        val component = UiComponent()
        component.init()
        stage.scene = Scene(component.base, WindowSettings.width, WindowSettings.height)
        stage.title = WindowSettings.title
        //stage.initStyle(StageStyle.UNDECORATED)
        stage.show()
    }
}
