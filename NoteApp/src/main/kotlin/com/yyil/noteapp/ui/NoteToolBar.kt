package com.yyil.noteapp.ui

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.settings.FontSettings
import javafx.scene.control.Button
import javafx.scene.control.MenuButton
import javafx.scene.control.MenuItem
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority


class NoteToolBar {

    private val buttonH = 20.0
    private val buttonW = 20.0

    private val fontMenu = MenuButton(FontSettings.STYLE)

    private val saveIconString = "toolbar-icons/save.png"
    private val saveIcon = ImageView()
    private val saveButton = Button("", saveIcon)

    private val fontSizeIconString = "toolbar-icons/font-size.png"
    private val fontSizeIcon = ImageView()
    private val fontSizeButton = Button("", fontSizeIcon)

    val toolBar = ToolBar()
    val toolBarLeft = HBox()
    val toolBarRight = HBox()


    fun init(){
        HBox.setHgrow(toolBarLeft, Priority.ALWAYS)
        HBox.setHgrow(toolBarRight, Priority.ALWAYS)

        initButtons()

        toolBar.items.add(toolBarLeft)
        toolBar.items.add(fontMenu)
        toolBar.items.add(fontSizeButton)
        toolBar.items.add(saveButton)
        toolBar.items.add(toolBarRight)
    }


    private fun initButtons(){
        fontMenu.items.addAll(
            MenuItem("Arial"),
            MenuItem("Comic Sans"))
        fontMenu.prefHeight = buttonH

        var icon = Image(NoteApplication::class.java.getResource(saveIconString).toString(),
        buttonW, buttonH, true, false)
        saveIcon.image = icon

        icon = Image(NoteApplication::class.java.getResource(fontSizeIconString).toString(),
            buttonW, buttonH, true, false)
        fontSizeIcon.image = icon
    }

}