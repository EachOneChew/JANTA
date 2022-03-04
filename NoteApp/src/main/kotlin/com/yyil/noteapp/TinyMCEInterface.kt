package com.yyil.noteapp

import com.yyil.noteapp.constant.ComponentConstant

import javafx.beans.property.StringProperty
import javafx.beans.property.SimpleStringProperty

import javafx.concurrent.Worker
import javafx.event.EventHandler
import javafx.scene.web.WebEngine
import javafx.scene.web.WebEvent
import javafx.scene.web.WebView
import netscape.javascript.JSObject


class TinyMCEInterface(initContent : String) {
    val content: StringProperty = SimpleStringProperty()

    val webView = WebView()
    private val webEngine: WebEngine = webView.engine

    private val url: String? = javaClass.classLoader.getResource(ComponentConstant.EDITOR_FILE)?.toExternalForm()
    private var editor: JSObject? = null

    inner class BridgeObject {
        var counter = 1

        fun setActiveEditor(ed : JSObject) {
            println("ACTIVE EDITOR SET")
            editor = ed
        }

        fun updateContent(newContent : String) {
            println("UPDATED CONTENT $counter")
            counter += 1
//            content.set(newContent)
        }
    }

    init {
        webEngine.load(url)

        webEngine.onAlert = EventHandler<WebEvent<String>> { e ->
            println(e.data)
        }

        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                (webEngine.executeScript("window") as JSObject)
                    .setMember("bridgeObject", BridgeObject())

                initEditor(initContent)

//                content.addListener { _, _, newString ->
//                    editor?.call("setContent", newString)
//                }
            }
        }
    }

    private fun initEditor(initContent : String) {
        webEngine.executeScript("window.initFunction('$initContent')")
    }

    private fun destroyEditor() {
        webEngine.executeScript("window.destroyFunction()")
    }
}