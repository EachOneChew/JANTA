package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.constant.ComponentConstant
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.scene.web.WebView

class NoteArea(tinyMCEView: WebView) : View {
    override val base = VBox()

    val testTextSync = Text()
    private val testTextScroll = ScrollPane(testTextSync)

    init {
        testTextScroll.maxHeight = ComponentConstant.TEST_SCROLL_PANE_HEIGHT
        testTextScroll.minHeight = ComponentConstant.TEST_SCROLL_PANE_HEIGHT

        base.children.add(tinyMCEView)
        base.children.add(testTextScroll)
        HBox.setHgrow(base, Priority.ALWAYS)
    }
}