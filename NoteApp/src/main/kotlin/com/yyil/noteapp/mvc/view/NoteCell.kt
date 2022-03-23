package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.constant.ComponentConstant
import com.yyil.noteapp.mvc.model.Note
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority
import javafx.util.Callback


class NoteCell : ListCell<Note>() {

    var note = Note()

    private val hBox = HBox()
    private var label: Label = Label()
    private val pane = Pane()

    init {
        HBox.setHgrow(pane, Priority.ALWAYS)

        hBox.alignment = Pos.CENTER
        hBox.children.addAll(label, pane)

        label.id = ComponentConstant.CELL_LABEL_ID
    }

    override fun updateItem(item: Note?, empty: Boolean) {
        super.updateItem(item, empty)
        text = null
        graphic = null
        prefHeight = ComponentConstant.LIST_CELL_HEIGHT;
        if (!empty && item != null) {
            label.text = item.title
            graphic = hBox
            note = item
        }
    }

}

class NotePlaceHolderCell : ListView<Any?>() {
    init {
        items.add(null)
        cellFactory = Callback {
            object : ListCell<Any?>() {
                override fun updateItem(item: Any?, empty: Boolean) {
                    super.updateItem(item, true)
                    prefHeight = ComponentConstant.LIST_CELL_HEIGHT;
                }
            }
        }
    }
}
