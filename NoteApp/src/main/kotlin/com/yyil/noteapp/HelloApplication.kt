package com.yyil.noteapp

import com.yyil.noteapp.ui.UiComponent
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


class HelloApplication : Application() {

    var windowW = 800.0
    var windowH = 600.0
    var title = "Note Taking App"

    override fun start(stage: Stage) {
        val component = UiComponent()
        component.init()
        stage.scene = Scene(component.base, windowW, windowH)
        stage.title = title
        //stage.initStyle(StageStyle.UNDECORATED)
        stage.show()
    }
}
