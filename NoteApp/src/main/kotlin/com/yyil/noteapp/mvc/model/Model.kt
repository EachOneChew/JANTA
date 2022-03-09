package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.TinyMCEInterface
import javafx.collections.FXCollections

class Model {
    val lightTheme = "oxide"
    val lightContent = "default"
    val darkTheme = "oxide-dark"
    val darkContent = "dark"
    var currentTheme = lightTheme

    val tinyMCE = TinyMCEInterface("TESTING INIT CONTENT", ::handleModelCall)

    val tempContent = FXCollections.observableArrayList(
        "You have opened Note1!", "Note2 Lorem Ipsum", "Note3 Huak Huak Huak", "Note4 READING WEAEK SOON"
    )

    fun handleNoteSelect(index: Int) {
        if (index < tempContent.size) {
            tinyMCE.selection = tempContent[index]
        }
    }

    /**
     * Example for Logan on how to receive event from interface
     */
    fun handleModelCall(target: String) {
        when (target) {
            "annotate" -> doSomething()
            "label" -> {} // TODO:  ModelCall.LABEL
        }
    }

    fun doSomething() {
        tinyMCE.selection = "HAH, YOU PRESSED ANNOTATE"
    }

    fun handleSwitchTheme() {
        if(currentTheme == lightTheme){
            tinyMCE.editorSkin = darkTheme
            tinyMCE.editorContentCSS = darkContent
            currentTheme = darkTheme
        }else{
            tinyMCE.editorSkin = lightTheme
            tinyMCE.editorContentCSS = lightContent
            currentTheme = lightTheme
        }
    }
}