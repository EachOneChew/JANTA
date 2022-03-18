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

    private val noteListIcon = ImageView()
    val noteListButton = ToggleButton("", noteListIcon)

    private val saveIcon = ImageView()
    val saveButton = Button("", saveIcon)

    private val themeIcon = ImageView()
    val themeButton = Button("", themeIcon)

    val toolBar = ToolBar()

    override val base = HBox()

    init {
        initButtons()

        toolBar.items.add(noteListButton)
        toolBar.items.add(saveButton)
        toolBar.items.add(themeButton)

        toolBar.orientation = Orientation.VERTICAL

        base.children.add(toolBar)
    }


    private fun initButtons() {
        noteListIcon.image = Image(
            NoteApplication::class.java.getResource(ComponentConstant.NOTE_LIST_ICON_PATH).toString(),
            ComponentConstant.BUTTON_ICON_WIDTH,
            ComponentConstant.BUTTON_ICON_HEIGHT,
            true, false
        )

        saveIcon.image = Image(
            NoteApplication::class.java.getResource(ComponentConstant.SAVE_ICON_PATH).toString(),
            ComponentConstant.BUTTON_ICON_WIDTH,
            ComponentConstant.BUTTON_ICON_HEIGHT,
            true, false
        )

        themeIcon.image = Image(
            NoteApplication::class.java.getResource(ComponentConstant.THEME_ICON_PATH).toString(),
            ComponentConstant.BUTTON_ICON_WIDTH,
            ComponentConstant.BUTTON_ICON_HEIGHT,
            true, false
        )
    }

}