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
     * The editor is sometimes uninitialized and null
     * Try to check that an editor exists if you're doing stuff
     * If you don't app won't crash, but nothing will happen
     */
    val isActive : Boolean
        get() = editorObj == null

    /**
     * Content in the editor as a StringProperty
     */
    val contentProp : StringProperty = SimpleStringProperty()

    /**
     * Content in the editor as a String
     * IMPORTANT
     * Changing this replaces all content in the editor and resets caret
     */
    var content : String
        get() = contentProp.value
        set(newContent) {
            editorObj?.call("setContent", newContent)
        }

    /**
     * Text currently selected by user
     * Changes to selection are properly reflected in the editor
     */
    var selection : String
        get() = (selectionObj?.call("getContent") ?: "") as String
        set(newContent) {
            selectionObj?.call("setContent", newContent)
        }

    /**
     * WebView for UI purposes, only Yixin should be using this probably
     */
    val webView = WebView()

    private val webEngine : WebEngine = webView.engine
    private val url : String? = javaClass.classLoader.getResource(ComponentConstant.EDITOR_FILE)?.toExternalForm()

    private val bridgeObj : BridgeObject = BridgeObject()
    private var editorObj : JSObject? = null
    private var selectionObj : JSObject? = null

    init {
        webEngine.load(url)

        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                (webEngine.executeScript("window") as JSObject)
                    .setMember("bridgeObj", bridgeObj)

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
            editorObj = ed
            selectionObj = editorObj?.getMember("selection") as JSObject?
        }

        fun setInterfaceContent(newContent : String) {
            contentProp.value = newContent
        }
    }
}