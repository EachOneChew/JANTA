package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteRepository
import com.yyil.noteapp.mvc.view.NoteToolBar
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.MouseEvent

class NoteToolBarController(
    val model: Model,
    noteToolBar: NoteToolBar,
    noteRepository: NoteRepository
) {
    init {
        val showListHandler = EventHandler { _: MouseEvent ->
            noteRepository.base.isVisible = !noteRepository.base.isVisible
            noteRepository.base.isManaged = !noteRepository.base.isManaged
        }
        noteToolBar.noteListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, showListHandler)
    }

    fun handleSwitchTheme(scene: Scene, noteToolBar: NoteToolBar, defaultStyle: String, darkModeStyle: String){

        val switchThemeHandler = EventHandler { _: MouseEvent ->
            if(model.currentTheme == model.lightTheme){
                model.handleSwitchTheme(model.darkTheme, model.darkContent)
                scene.stylesheets.clear()
                scene.stylesheets.add(darkModeStyle)
            }
            else{
                model.handleSwitchTheme(model.lightTheme, model.lightContent)
                scene.stylesheets.clear()
                scene.stylesheets.add(defaultStyle)
            }
        }
        noteToolBar.themeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, switchThemeHandler)
    }
}