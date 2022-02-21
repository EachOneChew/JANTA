package com.yyil.noteapp.ui

import com.yyil.noteapp.constant.ComponentConstant
import javafx.concurrent.Worker
import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.scene.web.WebEngine
import javafx.scene.web.WebEvent
import javafx.scene.web.WebView
import netscape.javascript.JSObject

object NoteArea {

    val base = VBox()

    val textArea = WebView()
    val webEngine: WebEngine = textArea.engine
    val url: String = javaClass.classLoader.getResource(ComponentConstant.EDITOR_FILE)?.toExternalForm() ?: "N/A"

    var testTextSync = Text("init")
    private val testTextScroll = ScrollPane(testTextSync)

    fun init(){

        println("init")
        webEngine.load(url)
        webEngine.onAlert = EventHandler<WebEvent<String>> { e ->
            testTextSync.text = e.data
        }

        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                NoteBaseUI.tinyMCE = (webEngine.executeScript(ComponentConstant.TINYMCE_SCRIPT) as JSObject)
                    .getMember("activeEditor") as JSObject
            }
        }

        testTextScroll.maxHeight = ComponentConstant.TEST_SCROLL_PANE_HEIGHT
        testTextScroll.minHeight = ComponentConstant.TEST_SCROLL_PANE_HEIGHT

        base.children.add(textArea)
        base.children.add(testTextScroll)
        HBox.setHgrow(base, Priority.ALWAYS)
    }

}