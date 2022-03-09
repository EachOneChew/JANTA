package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteRepository
import com.yyil.noteapp.mvc.view.NoteToolBar
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class NoteToolBarController(
    val model: Model,
    noteToolBar: NoteToolBar,
    noteRepository: NoteRepository
) {
    init {
        val showListHandler = EventHandler { _: MouseEvent ->
            noteRepository.noteList.isVisible = !noteRepository.noteList.isVisible
            noteRepository.noteList.isManaged = !noteRepository.noteList.isManaged
        }
        noteToolBar.noteListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, showListHandler)

        val switchThemeHandler = EventHandler { _: MouseEvent ->
            model.handleSwitchTheme()
        }
        noteToolBar.themeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, switchThemeHandler)

    }
}