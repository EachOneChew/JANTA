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
    val clearButton = Button()
    private val searchField = HBox(searchBar, clearButton)

    val labelList = ListView<String>()
    private val labelListScrollPane = ScrollPane(labelList)

    init {
        initButtons()

        searchBar.promptText = "Search..."

        /*labelList.items.addAll(
            "Label 1",
            "Label 2",
            "Label 3"
        )*/


        base.children.addAll(searchField, labelList)

        labelListScrollPane.fitToWidthProperty().set(true)
        base.fillWidthProperty().set(true)

        HBox.setHgrow(searchBar, Priority.ALWAYS)
        VBox.setVgrow(labelList, Priority.ALWAYS)

        base.isVisible = false
        base.isManaged = false
    }

    private fun initButtons() {
        clearButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.CLEAR_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )
    }
}