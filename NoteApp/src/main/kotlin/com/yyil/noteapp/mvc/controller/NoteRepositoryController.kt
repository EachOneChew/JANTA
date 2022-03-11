package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteRepository
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class NoteRepositoryController(
    val model: Model,
    noteRepository: NoteRepository
) {
    init {
        noteRepository.noteList.onMouseClicked = EventHandler { _: MouseEvent ->
            val i = noteRepository.noteList.selectionModel.selectedIndex
            model.handleNoteSelect(i)
        }
    }
}