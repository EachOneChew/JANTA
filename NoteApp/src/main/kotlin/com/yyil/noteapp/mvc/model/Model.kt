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
        if (newIndex < notes.size && newIndex >= 0) {
            if (currentIndex != newIndex) {
                /*
                if (currentIndex != null) {
                    tinyMCE.forceUpdate()
                    println("Current Idx $currentIndex; newIdx $newIndex")
                    notes[currentIndex!!].content = tinyMCE.content
                }
                */
                tinyMCE.content = notes[newIndex].content
                currentIndex = newIndex
            }
        }
    }

    /**
     * Example for Logan on how to receive event from interface
     * Label args example: ["definition", "Theorem 3.3.1", "label_134752"]
     */
    fun handleModelCall(target: String, arg: String) {
        when (target) {
//            "setAnnotationTitle" -> {
//                tinyMCE.selection = insertAnnotation(arg, tinyMCE.selection);
//                count++
//            }
//            "removeAnnotation" -> {
//                tinyMCE.selection = removeAnnotation(tinyMCE.selection)
//            }
            "label" -> TODO()
        }
    }

//    fun insertAnnotation(annotation: String, selection: String): String {
//        val openTag = "<span title=\"$annotation\">"
//        val closeTag = "</span>"
//        var result = selection
//            .replace(">(?=[^<])".toRegex(), ">$openTag")
//            .replace("(?<=[^>])<".toRegex(), "$closeTag<")
//
//        if (selection[0] != '<') {
//            result = "$openTag$result"
//        }
//
//        if (selection[selection.length - 1] != '>') {
//            result = "$result$closeTag"
//        }
//
//        return result
//    }
//
//    fun removeAnnotation(selection: String): String {
//        return selection
//            .replace("(<span [^>]+>)".toRegex(), "")
//            .replace("(</span>)".toRegex(), "")
//    }

    fun switchTheme(theme: String, content: String) {
        tinyMCE.appearance = Pair(theme, content)
        currentTheme = theme
    }

    fun retrieveNotes(): ObservableList<Note> {
        return FXCollections.observableArrayList(
            Note(0, "Note 1", "NOTE 1 CONTENT~"),
            Note(1, "Note 2", "note 2 content."),
            Note(2, "Note 3", "This is Note 3--")
        )
    }

    fun deleteNote(id: Int) {
        for (note in notes) {
            if (note.id == id) {
                notes.remove(note)
                break
            }
        }
    }
}