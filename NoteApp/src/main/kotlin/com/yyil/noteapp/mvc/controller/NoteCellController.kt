package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteCell
import javafx.event.EventHandler

class NoteCellController(
    val model: Model,
    noteCell: NoteCell
) {
    init {
        noteCell.deleteOption.onAction = EventHandler {
            model.deleteNote(noteCell.note)
            println("Delete note " + noteCell.note.title)
        }
    }
}