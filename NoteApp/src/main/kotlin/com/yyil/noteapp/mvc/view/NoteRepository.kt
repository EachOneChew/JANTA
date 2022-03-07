package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.constant.ComponentConstant
import javafx.collections.FXCollections
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox

class NoteRepository : View {
    override val base = HBox()

    val noteList = ListView<String>()
    private val listScroll = ScrollPane(noteList)
    val showListButton = Button("<")

    init {
        initNoteList()

        base.children.add(noteList)
        base.children.add(showListButton)
        base.fillHeightProperty().set(true)
    }

    private fun initNoteList () {
        //TODO: make items as Labels?
        val listItems = FXCollections.observableArrayList(
            "Note1", "Note2", "Note3", "Note4"
        )
        noteList.items.addAll(listItems)

        showListButton.prefHeight = Double.MAX_VALUE
        showListButton.id = ComponentConstant.SHOW_LIST_BUTTON_ID
    }

}