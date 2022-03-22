package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.mvc.model.Note
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox

class NoteRepository : View {
    override val base = HBox()

    val noteList = ListView<Note>()
    private val listScroll = ScrollPane(noteList)

    init {
        listScroll.fitToWidthProperty().set(true)
        listScroll.fitToHeightProperty().set(true)

        base.children.add(noteList)
        base.fillHeightProperty().set(true)
        base.isVisible = false
        base.isManaged = false

    }

}