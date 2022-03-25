package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.constant.ComponentConstant
import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.TextInputDialog
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox

class NoteToolBar : View {

    val noteListButton = ToggleButton()
    val labelButton = ToggleButton()
    val addButton = Button()
    val saveButton = Button()
    val themeButton = Button()

    val toolBar = ToolBar()

    val addNoteDialog = TextInputDialog()

    override val base = HBox()

    init {
        initButtons()

        toolBar.items.addAll(noteListButton, labelButton, addButton, saveButton, themeButton)

        toolBar.orientation = Orientation.VERTICAL

        addNoteDialog.graphic = null
        addNoteDialog.headerText = "Enter the title of the note:"

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
        noteListButton.style = "-fx-background-radius: 0"

        labelButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.LABEL_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )
        labelButton.style = "-fx-background-radius: 0"

        addButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.ADD_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )
        addButton.style = "-fx-background-radius: 0"

        saveButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.SAVE_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )
        saveButton.style = "-fx-background-radius: 0"

        themeButton.graphic = ImageView(
            Image(
                NoteApplication::class.java.getResource(ComponentConstant.THEME_ICON_PATH).toString(),
                ComponentConstant.BUTTON_ICON_WIDTH,
                ComponentConstant.BUTTON_ICON_HEIGHT,
                true, false
            )
        )
        themeButton.style = "-fx-background-radius: 0"
    }

}