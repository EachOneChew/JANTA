package com.yyil.noteapp.mvc.view

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

    private fun initNoteList() {
        //TODO: After making items as Labels, enable wrapText property to disable horizontal scroll

        val listItems = FXCollections.observableArrayList(
            "Note1", "Note2", "Note3", "Note4"
        )
        noteList.items.addAll(listItems)

        showListButton.prefHeight = Double.MAX_VALUE
        showListButton.id = "sidebar-button"
    }

}