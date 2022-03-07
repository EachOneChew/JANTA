package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteRepository
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class NoteRepositoryController (
    val model: Model,
    noteRepository: NoteRepository
) {
    init {
        val showListHandler = EventHandler { _ : MouseEvent ->
            noteRepository.noteList.isVisible = !noteRepository.noteList.isVisible
            noteRepository.noteList.isManaged = !noteRepository.noteList.isManaged

            noteRepository.showListButton.text =
                if (noteRepository.showListButton.text == "<") ">" else "<"
        }
        noteRepository.showListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, showListHandler)

        noteRepository.noteList.onMouseClicked = EventHandler { _ : MouseEvent ->
            val i = noteRepository.noteList.selectionModel.selectedIndex
            model.handleNoteSelect(i)
        }
    }
}