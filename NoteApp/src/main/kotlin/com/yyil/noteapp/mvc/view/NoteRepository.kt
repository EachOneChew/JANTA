package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.constant.ComponentConstant
import com.yyil.noteapp.mvc.model.Note
import javafx.collections.FXCollections
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox

class NoteRepository : View {
    override val base = HBox()

    val noteList = ListView<Note>()
    private val listScroll = ScrollPane(noteList)

    init {
        initNoteList()

        base.children.add(listScroll)
        listScroll.isFitToWidth = true
        listScroll.isFitToHeight = true

        base.fillHeightProperty().set(true)
        base.id = ComponentConstant.NOTE_REPO_ID
        base.isVisible = false
        base.isManaged = false
    }

    private fun initNoteList() {
        //TODO: make items as Labels?
        val listItems = FXCollections.observableArrayList<Note>(
            Note(0, "Note 1"),
            Note(1, "Note 2"),
            Note(2, "Note 3")
        )
        noteList.items.addAll(listItems)

        noteList.setCellFactory { NoteCell() }
    }

}