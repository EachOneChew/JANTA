package com.yyil.noteapp

import com.yyil.noteapp.constant.ComponentConstant
import com.yyil.noteapp.ui.NoteArea
import javafx.collections.FXCollections
import javafx.concurrent.Worker
import javafx.event.EventHandler
import javafx.scene.web.WebEngine
import javafx.scene.web.WebEvent
import javafx.scene.web.WebView
import netscape.javascript.JSObject

class TinyMCEInterface (val noteArea : NoteArea) {

    val editor = WebView()
    val webEngine: WebEngine = editor.engine
    val url: String = javaClass.classLoader.getResource(ComponentConstant.EDITOR_FILE)?.toExternalForm() ?: "N/A"
    lateinit var tinyMCE: JSObject

    //Code in NoteArea
    fun initTinyMCE() {
        webEngine.load(url)
        webEngine.onAlert = EventHandler<WebEvent<String>> { e ->
            noteArea.testTextSync.text = e.data
        }

        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                tinyMCE = (webEngine.executeScript(ComponentConstant.TINYMCE_SCRIPT) as JSObject)
                    .getMember("activeEditor") as JSObject
            }
        }

    }


    //Code in NoteRepo
    /*    noteList.onMouseClicked = EventHandler {
            val i = noteList.selectionModel.selectedIndex
            val tempContent = FXCollections.observableArrayList(
                "You have opened Note1!", "Note2 Lorem Ipsum", "Note3 Huak Huak Huak", "Note4 READING WEAEK SOON"
            )
            tinyMCE.call("setContent", tempContent[i])
        }

     */
}