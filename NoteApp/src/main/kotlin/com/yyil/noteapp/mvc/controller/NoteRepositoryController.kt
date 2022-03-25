package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteCell
import com.yyil.noteapp.mvc.view.NoteEditMenu
import com.yyil.noteapp.mvc.view.NotePlaceHolderCell
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

        noteRepository.noteList.items = model.notes

        noteRepository.noteList.placeholder = NotePlaceHolderCell()

        noteRepository.noteList.setCellFactory {

            var noteCell = NoteCell()

            var noteEditMenu = NoteEditMenu(noteCell)
            NoteEditMenuController(model, noteEditMenu, noteRepository)

            noteCell
        }
    }
}

