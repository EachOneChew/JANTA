package com.yyil.noteapp.mvc.view

import com.yyil.noteapp.constant.ComponentConstant
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextArea
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.web.WebView

class NoteArea(tinyMCEView: WebView) : View {
    override val base = VBox()

    val testTextSync = TextArea()
    private val testTextScroll = ScrollPane(testTextSync)

    init {
        testTextSync.id = ComponentConstant.TEST_TEXT_ID

        testTextScroll.maxHeight = ComponentConstant.TEST_SCROLL_PANE_HEIGHT
        testTextScroll.minHeight = ComponentConstant.TEST_SCROLL_PANE_HEIGHT
        testTextScroll.isFitToWidth = true
        testTextScroll.hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        testTextSync.wrapTextProperty().set(true)

        base.children.add(tinyMCEView)
        base.children.add(testTextSync)
        HBox.setHgrow(base, Priority.ALWAYS)
    }
}