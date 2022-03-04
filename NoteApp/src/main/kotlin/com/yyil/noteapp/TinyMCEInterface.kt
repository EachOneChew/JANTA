package com.yyil.noteapp

import com.yyil.noteapp.constant.ComponentConstant

import javafx.beans.property.StringProperty
import javafx.beans.property.SimpleStringProperty

import javafx.concurrent.Worker
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import netscape.javascript.JSObject

class TinyMCEInterface(initContent : String) {
    /**
     * Content in the editor, handles changes as one would expect
     * Get with content.get()
     * Set with content.set(_)
     */
    val content: StringProperty = SimpleStringProperty()

    class Selection {
        fun getSelectedContent() : String {
            return "hihi"
        }

        fun setSelectedContent() : Int {
            return 0
        }
    }

    /**
     * Text currently selected by user, the Selection class have intuitively named methods
     */
    val selection : Selection
        get() = Selection()

    /**
     * WebView for UI purposes, only Yixin should be using this probably
     */
    val webView = WebView()
    private val webEngine: WebEngine = webView.engine

    private val url: String? = javaClass.classLoader.getResource(ComponentConstant.EDITOR_FILE)?.toExternalForm()
    private var editor: JSObject? = null

    /**
     * Can't be private because of Javascript shenanigans, please don't use
     */
    inner class BridgeObject {
        fun setActiveEditor(ed : JSObject) {
            editor = ed
        }

        fun updateContent(newContent : String) {
            content.set(newContent)
        }
    }

    private val bridgeObject : BridgeObject = BridgeObject()

    init {
        webEngine.load(url)

        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                (webEngine.executeScript("window") as JSObject)
                    .setMember("bridgeObject", bridgeObject)

                initEditor(initContent)

                content.addListener { _, _, newString ->
                    editor?.call("setContent", newString)
                }
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