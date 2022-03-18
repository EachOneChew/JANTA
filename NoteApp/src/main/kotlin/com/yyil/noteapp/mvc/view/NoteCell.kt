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


class NoteCell : ListCell<Note>(){

    var model = Note(-1, "")

    val hbox = HBox()
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
    }

    override fun updateItem(item: Note?, empty: Boolean) {
        super.updateItem(item, empty)
        text = null
        if (empty || item == null) {
            graphic = null
        } else {
            label.text = item.title
            graphic = hbox
            model = item
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