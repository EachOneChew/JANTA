package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.constant.ComponentConstant
import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox

class NoteToolBar : View {

    private val noteListIconString = "icons/folder.png"
    private val noteListIcon = ImageView()
    val noteListButton = Button("", noteListIcon)

    private val saveIconString = "icons/save.png"
    private val saveIcon = ImageView()
    val saveButton = Button("", saveIcon)

    private val themeIconString = "icons/theme.png"
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
            NoteApplication::class.java.getResource(noteListIconString).toString(),
            ComponentConstant.BUTTON_ICON_WIDTH,
            ComponentConstant.BUTTON_ICON_HEIGHT,
            true, false
        )

        saveIcon.image = Image(
            NoteApplication::class.java.getResource(saveIconString).toString(),
            ComponentConstant.BUTTON_ICON_WIDTH,
            ComponentConstant.BUTTON_ICON_HEIGHT,
            true, false
        )

        themeIcon.image = Image(
            NoteApplication::class.java.getResource(themeIconString).toString(),
            ComponentConstant.BUTTON_ICON_WIDTH,
            ComponentConstant.BUTTON_ICON_HEIGHT,
            true, false
        )
    }

}