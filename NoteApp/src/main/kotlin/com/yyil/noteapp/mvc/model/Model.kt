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
                if (currentIndex != null) {
                    tinyMCE.forceUpdate()
                    notes[currentIndex!!].content = tinyMCE.content
                }
                tinyMCE.content = notes[newIndex].content
                currentIndex = newIndex
            }
        }
    }

    /**
     * Example for Logan on how to receive event from interface
     * Label args example: ["definition", "Theorem 3.3.1", "label_134752"]
     */
    fun handleModelCall(target: String, arg: List<String>) {
        when (target) {
            "label" -> if (currentIndex != null) {
                if (notes[currentIndex!!].labels.containsKey<String>(arg[0])){
                    notes[currentIndex!!].labels[arg[0]]?.put(arg[1], arg[2]) // Will overwrite if the name already exists
                }
                else {
                    val nmap = mutableMapOf<String, String>()
                    nmap[arg[1]] = arg[2]
                    notes[currentIndex!!].labels[arg[0]] = nmap
                }
            }
        }
    }

    fun switchTheme(theme: String, content: String) {
        tinyMCE.appearance = Pair(theme, content)
        currentTheme = theme
    }

    fun retrieveNotes(): ObservableList<Note> {
        return FXCollections.observableArrayList(
            Note(0, "Note 1", "NOTE 1 CONTENT~", mutableMapOf("hello" to mutableMapOf("hi" to "sup"))),
            Note(1, "Note 2", "note 2 content."),
            Note(2, "Note 3", "This is Note 3--")
        )
    }

    fun retrieveLabels(): MutableSet<String> {
        if (currentIndex != null) {
            return notes[currentIndex!!].labels.keys
        }
        return mutableSetOf()
    }

    fun deleteNote(note: Note) {
        notes.remove(note)
    }
}