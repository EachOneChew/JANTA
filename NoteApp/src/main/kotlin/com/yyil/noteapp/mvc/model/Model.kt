package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.TinyMCEInterface
import javafx.collections.FXCollections
import javafx.collections.ObservableList

class Model {
    val tinyMCE = TinyMCEInterface("", ::handleModelCall)

    val notes = retrieveNotes()

    val lightTheme = "oxide"
    val lightContent = "default"
    val darkTheme = "dark-mode"
    val darkContent = "dark-mode"

    var count = 0
    var currentTheme = lightTheme
    var currentIndex: Int? = null

    fun handleNoteSelect(newIndex: Int) {
        if (newIndex < notes.size) {
            if (currentIndex != null && currentIndex != newIndex) {
                tinyMCE.forceUpdate()
                notes[currentIndex!!] = tinyMCE.content
            }
            tinyMCE.content = notes[newIndex]
            currentIndex = newIndex
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
        tinyMCE.appearance = Pair(theme, content)
        currentTheme = theme
    }

    fun retrieveNotes(): ObservableList<String> {
        return FXCollections.observableArrayList(
            "You have opened Note1!", "Note2 Lorem Ipsum", "Note3 Huak Huak Huak", "Note4 READING WEAEK SOON"
        )
    }
}