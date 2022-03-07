package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteArea

class NoteAreaController(
    val model: Model,
    noteArea: NoteArea
) {
    init {
        println("NOTEAREA INIT RAN")
        noteArea.testTextSync.textProperty().bind(model.tinyMCE.contentProp)
    }
}