package com.yyil.noteapp.ui

import com.yyil.noteapp.constant.ComponentConstant
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import netscape.javascript.JSObject

class NoteRepository {
    val base = HBox()

    val noteList = ListView<String>()
    private val listScroll = ScrollPane(noteList)
    val showListButton = Button("<")

    private fun initNoteList (tinyMCE: JSObject) {
        val leftListItems = FXCollections.observableArrayList(
            "Note1", "Note2", "Note3", "Note4"
        )
        noteList.items = leftListItems

        noteList.onMouseClicked = EventHandler {
            val i = noteList.selectionModel.selectedIndex
            val tempContent = FXCollections.observableArrayList(
                "You have opened Note1!", "Note2 Lorem Ipsum", "Note3 Huak Huak Huak", "Note4 READING WEAEK SOON"
            )
            tinyMCE.call("setContent", tempContent[i])
        }

        showListButton.prefHeight = Double.MAX_VALUE
        showListButton.minWidth = ComponentConstant.MIN_BUTTON_WIDTH
        showListButton.style = "-fx-background-radius: 0"

        val showListHandler = EventHandler{
                event : MouseEvent ->
            println("Button Clicked (" + event.x + "," + event.y + ")")
            noteList.isVisible = !noteList.isVisible
            noteList.isManaged = !noteList.isManaged
            showListButton.text = if(showListButton.text == "<") ">" else "<"
        }
        showListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, showListHandler)
    }

    fun init(tinyMCE: JSObject){
        initNoteList(tinyMCE)

        base.children.add(noteList)
        base.children.add(showListButton)
        base.fillHeightProperty().set(true)
    }

}