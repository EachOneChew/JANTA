package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.constant.ComponentConstant
import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox

class NoteToolBar: View {

    val noteListButton = ToggleButton()
    val saveButton = Button()
    val addButton = Button()
    val labelButton = ToggleButton()
    val themeButton = Button()

    val toolBar = ToolBar()

    override val base = HBox()

    init {
        initButtons()

        toolBar.items.addAll(noteListButton, saveButton, addButton, labelButton, themeButton)

        toolBar.orientation = Orientation.VERTICAL

        base.children.add(toolBar)
    }


    private fun initButtons() {
        noteListButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.NOTE_LIST_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )

        addButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.ADD_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )

        saveButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.SAVE_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )

        themeButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.THEME_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )

        labelButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.LABEL_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )
    }

}