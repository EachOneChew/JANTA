package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.constant.ComponentConstant
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox

class LabelList : View {
    override val base = VBox()

    val searchBar = TextField()
    private val searchField = HBox(searchBar)

    val labelList = ListView<String>()
    private val labelListScrollPane = ScrollPane(labelList)

    init {
        searchBar.style = "-fx-background-radius: 0"

        searchBar.promptText = "Search..."

        base.children.addAll(searchField, labelList)

        labelListScrollPane.fitToWidthProperty().set(true)
        base.fillWidthProperty().set(true)

        HBox.setHgrow(searchBar, Priority.ALWAYS)
        VBox.setVgrow(labelList, Priority.ALWAYS)

        base.isVisible = false
        base.isManaged = false
    }
}