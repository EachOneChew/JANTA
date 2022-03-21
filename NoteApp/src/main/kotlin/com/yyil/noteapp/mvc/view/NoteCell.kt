package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.constant.ComponentConstant
import com.yyil.noteapp.mvc.controller.NoteCellController
import com.yyil.noteapp.mvc.model.Note
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.MenuButton
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority


class NoteCell : ListCell<Note>() {

    var note = Note(-1, "")
    lateinit var controller: NoteCellController

    private val hbox = HBox()
    private var label: Label = Label()
    private val pane = Pane()
    private var dropDown = MenuButton()

    val renameOption = MenuItem("Rename")
    val deleteOption = MenuItem("Delete")

    private val expandIcon = ImageView(
        Image(
            NoteApplication::class.java.getResource(ComponentConstant.DOTS_ICON_PATH).toString(),
            ComponentConstant.BUTTON_ICON_WIDTH,
            ComponentConstant.BUTTON_ICON_HEIGHT,
            true, true
        )
    )

    init {
        dropDown.items.addAll(renameOption, deleteOption)
        dropDown.graphic = expandIcon

        HBox.setHgrow(pane, Priority.ALWAYS)

        hbox.alignment = Pos.CENTER
        hbox.children.addAll(label, pane, dropDown)

        label.id = ComponentConstant.CELL_LABEL_ID
    }

    override fun updateItem(item: Note?, empty: Boolean) {
        super.updateItem(item, empty)
        text = null
        if (empty || item == null) {
            graphic = null
        } else {
            label.text = item.title
            graphic = hbox
            note = item
        }
    }

}


/*
class NoteCellFactory : Callback<ListView<Note?>?, ListCell<Note>?> {
    override fun call(param: ListView<NoteCell?>?): ListCell<NoteCell> {
        return ListCell<NoteCell>()
        }
    }
}

 */