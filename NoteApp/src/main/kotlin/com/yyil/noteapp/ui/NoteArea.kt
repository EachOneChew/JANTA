package com.yyil.noteapp.ui

import com.yyil.noteapp.TinyMCEInterface
import com.yyil.noteapp.constant.ComponentConstant
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Text

class NoteArea(tinyMCEInterface : TinyMCEInterface) : View {

    override val base = VBox()

    private val testTextSync = Text()
    private val testTextScroll = ScrollPane(testTextSync)
    private var tinyMCE : TinyMCEInterface

    init {
        tinyMCE = tinyMCEInterface
        testTextScroll.maxHeight = ComponentConstant.TEST_SCROLL_PANE_HEIGHT
        testTextScroll.minHeight = ComponentConstant.TEST_SCROLL_PANE_HEIGHT

        base.children.add(tinyMCE.webView)
        base.children.add(testTextScroll)
        HBox.setHgrow(base, Priority.ALWAYS)
    }
}