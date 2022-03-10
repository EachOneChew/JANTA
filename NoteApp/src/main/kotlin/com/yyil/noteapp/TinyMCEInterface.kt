package com.yyil.noteapp

import com.yyil.noteapp.constant.ComponentConstant
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.concurrent.Worker
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import netscape.javascript.JSObject

/**
 * DO NOT MOVE THIS FILE UNDER ANY CIRCUMSTANCE
 * FOR SOME GOD FORSAKEN REASON THIS HAS TO BE IN ROOT FOLDER
 */
class TinyMCEInterface(
    initContent: String,
    private val handleModelCall: (String) -> Unit
) {
    /**
     * The editor is sometimes uninitialized and null
     * Try to check that an editor exists if you're doing stuff
     * If you don't app won't crash, but nothing will happen
     */
    val isActive: Boolean
        get() = editorObj != null && selectionObj != null && initOptionsObj != null

    /**
     * Content in the editor as a StringProperty
     */
    val contentProp: StringProperty = SimpleStringProperty()

    /**
     * Content in the editor as a String
     * IMPORTANT: changing this replaces all content in the editor and resets caret
     */
    var content: String
        get() = contentProp.value
        set(newContent) {
            editorObj?.call("setContent", newContent)
        }

    /**
     * Text currently selected by user
     * Changes to selection are properly reflected in the editor
     */
    var selection: String
        get() = (selectionObj?.call("getContent") ?: "") as String
        set(newContent) {
            selectionObj?.call("setContent", newContent)
        }

    /**
     * "skin" property in editor init function
     * WARNING: MODIFYING WILL DESTROY AND REINITIALIZE EDITOR
     */
    var editorSkin: String
        get() = initOptionsObj?.getMember("skin") as String
        set(newSkin) {
            initOptionsObj?.setMember("skin", newSkin)
            destroyEditor()
            initEditor("CHANGED SKIN TO $newSkin")
        }

    /**
     * "content_css" property in editor init function
     * WARNING: MODIFYING WILL DESTROY AND REINITIALIZE EDITOR
     */
    var editorContentCSS: String
        get() = initOptionsObj?.getMember("content_css") as String
        set(newContentCSS) {
            initOptionsObj?.setMember("content_css", newContentCSS)
            destroyEditor()
            initEditor("CHANGED CONTENT CSS TO $newContentCSS")
        }

    /**
     * WebView for UI purposes, only Yixin should be using this probably
     */
    val webView = WebView()

    private val webEngine: WebEngine = webView.engine
    private val url: String? = javaClass.classLoader.getResource(ComponentConstant.EDITOR_FILE)?.toExternalForm()

    private val bridgeObj: BridgeObject = BridgeObject()
    private var editorObj: JSObject? = null
    private var selectionObj: JSObject? = null
    private var initOptionsObj: JSObject? = null

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

    fun initEditor(initContent: String) {
        webEngine.executeScript("window.initFunction('$initContent')")
    }

    fun destroyEditor() {
        webEngine.executeScript("window.destroyFunction()")
    }

    /**
     * Can't be private because of Javascript shenanigans, please don't use
     */
    inner class BridgeObject {
        fun setObjs(ed: JSObject?) {
            if (ed != null) {
                editorObj = ed
                selectionObj = ed.getMember("selection") as JSObject
                initOptionsObj = webEngine.executeScript("window.initOptions") as JSObject
            } else {
                editorObj = null
                selectionObj = null
                initOptionsObj = null
            }
        }

        fun setInterfaceContent(newContent: String) {
            contentProp.value = newContent
        }

        fun callModel(target: String) {
            handleModelCall(target)
        }

//        fun printDebug(msg : String) {
//            println(msg)
//        }
    }
}