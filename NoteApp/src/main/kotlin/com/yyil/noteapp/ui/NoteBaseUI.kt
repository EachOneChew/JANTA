package com.yyil.noteapp.ui

import javafx.scene.layout.HBox


class NoteBaseUI {

    val base = HBox()
    val noteArea = NoteArea()
    val noteRepository = NoteRepository()

    fun init() {
        noteArea.init()
        noteRepository.init(noteArea.tinyMCE)

        base.children.add(noteRepository.base)
        base.children.add(noteArea.base)
        base.fillHeightProperty().set(true)
    }
}
