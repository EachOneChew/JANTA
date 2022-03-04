package com.yyil.noteapp.controller

import com.yyil.noteapp.ui.NoteRepository
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class NoteRepositoryController (val noteRepository: NoteRepository) {

    fun handleEvents() {
        val showListHandler = EventHandler { _: MouseEvent ->
            noteRepository.noteList.isVisible = !noteRepository.noteList.isVisible
            noteRepository.noteList.isManaged = !noteRepository.noteList.isManaged

            noteRepository.showListButton.text =
                if (noteRepository.showListButton.text == "<") ">" else "<"
        }
        noteRepository.showListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, showListHandler)

    }
}