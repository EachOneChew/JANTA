package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.constant.ComponentConstant
import com.yyil.noteapp.svg.BufferedImageTranscoder
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.TextInputDialog
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class NoteToolBar : View {

    val noteListButton = ToggleButton()
    val saveButton = Button()
    val addButton = Button()
    val labelButton = ToggleButton()
    val themeButton = Button()

    val toolBar = ToolBar()
    private val vBox = VBox()
    val addNoteDialog = TextInputDialog()

    override val base = HBox()

    init {
        initButtons()

        vBox.children.addAll(noteListButton, saveButton, addButton, labelButton, themeButton)
        vBox.alignment = Pos.CENTER
        vBox.spacing = ComponentConstant.TOOLBAR_SPACING

        toolBar.orientation = Orientation.VERTICAL
        toolBar.prefWidth = ComponentConstant.TOOLBAR_WIDTH
        toolBar.maxWidth = ComponentConstant.TOOLBAR_WIDTH
        toolBar.items.add(vBox)

        addNoteDialog.graphic = null
        addNoteDialog.headerText = "Enter the title of the note:"
        addNoteDialog.title = ""

        base.children.add(toolBar)
    }


    private fun initButtons() {

        noteListButton.graphic = BufferedImageTranscoder.transcodeToImageView(
            NoteApplication::class.java.getResource(ComponentConstant.NOTE_LIST_ICON_PATH).toString()
        )

        addButton.graphic = BufferedImageTranscoder.transcodeToImageView(
            NoteApplication::class.java.getResource(ComponentConstant.ADD_ICON_PATH).toString()
        )

        saveButton.graphic = BufferedImageTranscoder.transcodeToImageView(
            NoteApplication::class.java.getResource(ComponentConstant.SAVE_ICON_PATH).toString()
        )

        themeButton.graphic = BufferedImageTranscoder.transcodeToImageView(
            NoteApplication::class.java.getResource(ComponentConstant.THEME_ICON_PATH).toString()
        )

        labelButton.graphic = BufferedImageTranscoder.transcodeToImageView(
            NoteApplication::class.java.getResource(ComponentConstant.LABEL_ICON_PATH).toString()
        )
    }

}