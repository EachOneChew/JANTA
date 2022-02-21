package com.yyil.noteapp.ui

import javafx.scene.layout.HBox
import netscape.javascript.JSObject


object NoteBaseUI {

    val base = HBox()
    lateinit var tinyMCE: JSObject

    fun init() {
        NoteArea.init()
        NoteRepository.init()

        base.children.add(NoteRepository.base)
        base.children.add(NoteArea.base)
        base.fillHeightProperty().set(true)
    }
}
