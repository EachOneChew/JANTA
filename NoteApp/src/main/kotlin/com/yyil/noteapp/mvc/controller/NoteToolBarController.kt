package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.LabelList
import com.yyil.noteapp.mvc.view.NoteRepository
import com.yyil.noteapp.mvc.view.NoteToolBar
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.MouseEvent

class NoteToolBarController(
    val model: Model,
    noteToolBar: NoteToolBar,
    noteRepository: NoteRepository,
    labelList: LabelList
) {
    init {
        val noteListHandler = EventHandler { _: MouseEvent ->
            if (labelList.base.isVisible) {
                labelList.base.isVisible = false
                labelList.base.isManaged = false
                noteToolBar.labelButton.fire()
            }
            noteRepository.base.isVisible = !noteRepository.base.isVisible
            noteRepository.base.isManaged = !noteRepository.base.isManaged
        }
        noteToolBar.noteListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, noteListHandler)

        val labelListHandler = EventHandler { _: MouseEvent ->
            if (noteRepository.base.isVisible) {
                noteRepository.base.isVisible = false
                noteRepository.base.isManaged = false
                noteToolBar.noteListButton.fire()
            }
            labelList.base.isVisible = !labelList.base.isVisible
            labelList.base.isManaged = !labelList.base.isManaged
        }
        noteToolBar.labelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, labelListHandler)

    }

    fun handleSwitchTheme(scene: Scene, noteToolBar: NoteToolBar, defaultStyle: String, darkModeStyle: String) {

        val switchThemeHandler = EventHandler { _: MouseEvent ->
            if (model.currentTheme == model.lightTheme) {
                model.handleSwitchTheme(model.darkTheme, model.darkContent)
                scene.stylesheets.clear()
                scene.stylesheets.add(darkModeStyle)
            } else {
                model.handleSwitchTheme(model.lightTheme, model.lightContent)
                scene.stylesheets.clear()
                scene.stylesheets.add(defaultStyle)
            }
        }
        noteToolBar.themeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, switchThemeHandler)
    }
}