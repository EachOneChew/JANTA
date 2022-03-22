package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteCell
import com.yyil.noteapp.mvc.view.NoteRepository
import javafx.event.EventHandler

class NoteRepositoryController(
    val model: Model,
    noteRepository: NoteRepository
) {
    init {
        noteRepository.noteList.onMouseClicked = EventHandler {
            val i = noteRepository.noteList.selectionModel.selectedIndex
            model.handleNoteSelect(i)
        }

        noteRepository.noteList.items.addAll(model.notes)

        noteRepository.noteList.setCellFactory {
            val noteCell = NoteCell()
            noteCell.controller = NoteCellController(model, noteCell)
            noteCell
        }
    }
}
