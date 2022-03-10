package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.TinyMCEInterface
import javafx.collections.FXCollections

class Model {
    val lightTheme = "oxide"
    val lightContent = "default"
    val darkTheme = "dark-mode"
    val darkContent = "dark-mode"
    var currentTheme = lightTheme

    val tinyMCE = TinyMCEInterface("", ::handleModelCall)

    val tempContent = FXCollections.observableArrayList(
        "You have opened Note1!", "Note2 Lorem Ipsum", "Note3 Huak Huak Huak", "Note4 READING WEAEK SOON"
    )

    var count = 0

    fun handleNoteSelect(index: Int) {
        if (index < tempContent.size) {
            tinyMCE.content = tempContent[index]
        }
    }

    /**
     * Example for Logan on how to receive event from interface
     */
    fun handleModelCall(target: String) {
        when (target) {
            "addAnnotation" -> {
                tinyMCE.selection = insertAnnotation("yo number:$count", tinyMCE.selection);
                count++
            }
            "removeAnnotation" -> {
                tinyMCE.selection = removeAnnotation(tinyMCE.selection)
            }
            "label" -> TODO()
        }
    }

    fun insertAnnotation(annotation: String, selection: String): String {
        val openTag = "<span title=\"$annotation\">"
        val closeTag = "</span>"
        var result = selection
            .replace(">(?=[^<])".toRegex(), ">$openTag")
            .replace("(?<=[^>])<".toRegex(), "$closeTag<")

        if (selection[0] != '<') {
            result = "$openTag$result"
        }

        if (selection[selection.length - 1] != '>') {
            result = "$result$closeTag"
        }

        return result
    }

    fun removeAnnotation(selection: String): String {
        return selection
            .replace("(<span [^>]+>)".toRegex(), "")
            .replace("(</span>)".toRegex(), "")
    }

    fun handleSwitchTheme(theme: String, content: String) {
        tinyMCE.editorSkin = theme
        tinyMCE.editorContentCSS = content
        currentTheme = theme
    }
}