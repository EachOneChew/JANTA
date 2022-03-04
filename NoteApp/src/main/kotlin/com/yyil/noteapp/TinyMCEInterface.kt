package com.yyil.noteapp

import com.yyil.noteapp.constant.ComponentConstant

import javafx.beans.property.StringProperty
import javafx.beans.property.SimpleStringProperty

import javafx.concurrent.Worker
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import netscape.javascript.JSObject

class TinyMCEInterface(initContent : String) {
    val isActive : Boolean
        get() = editor == null

    /**
     * Content in the editor, handles changes as one would expect
     * Get with content.get()
     * If you want to change content, please call replaceContent
     */
    val content : StringProperty = SimpleStringProperty()

    /**
     * Replaces content in editor, resets the caret
     */
    fun replaceContent(newContent : String) {
        editor?.call("setContent", newContent)
    }

    /**
     * Text currently selected by user, the Selection class have intuitively named methods
     */
    var selectionObject : SelectionObject = SelectionObject()

    /**
     * WebView for UI purposes, only Yixin should be using this probably
     */
    val webView = WebView()

    private val webEngine : WebEngine = webView.engine
    private val url : String? = javaClass.classLoader.getResource(ComponentConstant.EDITOR_FILE)?.toExternalForm()
    private val bridgeObject : BridgeObject = BridgeObject()

    private var editor : JSObject? = null
    private var selection : JSObject? = null

    init {
        webEngine.load(url)

        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                (webEngine.executeScript("window") as JSObject)
                    .setMember("bridgeObject", bridgeObject)

                initEditor(initContent)
            }
        }
    }

    private fun initEditor(initContent : String) {
        webEngine.executeScript("window.initFunction('$initContent')")
    }

    private fun destroyEditor() {
        webEngine.executeScript("window.destroyFunction()")
    }

    /**
     * Can't be private because of Javascript shenanigans, please don't use
     */
    inner class BridgeObject {
        fun setEditorAndSelection(ed : JSObject?) {
            editor = ed
            selection = editor?.getMember("selection") as JSObject?
        }

        fun setInterfaceContent(newContent : String) {
            content.set(newContent)
        }
    }

    inner class SelectionObject() {
        fun getSelectedContent() : String {
            return (selection?.call("getContent") ?: "") as String
        }

        fun setSelectedContent(newContent : String) {
            selection?.call("setContent", newContent)
        }
    }
}