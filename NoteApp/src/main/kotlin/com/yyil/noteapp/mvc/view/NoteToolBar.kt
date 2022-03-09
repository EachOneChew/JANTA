package com.yyil.noteapp.mvc.view

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

/**
 * USELESS, FOR NOW
 */
class NoteToolBar : View {

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
    private val toolBarLeft = HBox()
    private val toolBarRight = HBox()

    override val base = HBox()

    init {
        HBox.setHgrow(toolBarLeft, Priority.ALWAYS)
        HBox.setHgrow(toolBarRight, Priority.ALWAYS)

        initButtons()

        toolBar.items.add(fontMenu)
        toolBar.items.add(fontSizeButton)
        toolBar.items.add(saveButton)

        base.children.add(toolBarLeft)
        base.children.add(toolBar)
        base.children.add(toolBarRight)
    }


    private fun initButtons() {
        fontMenu.items.addAll(
            MenuItem("Arial"),
            MenuItem("Comic Sans")
        )
        fontMenu.prefHeight = buttonH

        saveIcon.image = Image(
            NoteApplication::class.java.getResource(saveIconString).toString(),
            buttonW, buttonH, true, false
        )

        fontSizeIcon.image = Image(
            NoteApplication::class.java.getResource(fontSizeIconString).toString(),
            buttonW, buttonH, true, false
        )
    }

}