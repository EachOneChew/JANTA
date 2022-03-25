package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.TinyMCEInterface
import javafx.collections.FXCollections
import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList

class Model {
    val tinyMCE = TinyMCEInterface("", ::handleModelCall)

    val notes = retrieveNotes()
    var label = mapOf<String, String>()
    val listLabel: ObservableList<String> = observableArrayList()

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

                    label = notes[newIndex].labels
                    listLabel.clear()
                    for (key in label.keys) {
                        listLabel.add(key + " " + label[key])
                    }
                }
                tinyMCE.content = notes[newIndex].content
                currentIndex = newIndex
            }
        }
    }

    /**
     * Example for Logan on how to receive event from interface
     * Label args example: ["definition", "Theorem 3.3.1"] a.k.a. [TYPE, TITLE]
     */
    fun handleModelCall(target: String, type: String, title: String) {
        when (target) {
            "addLabel" ->
                if (currentIndex != null) {
                    notes[currentIndex!!].labels[title] = type
                    listLabel.add("$title $type")
                }
            "removeLabel" ->
                if (currentIndex != null) {
                    notes[currentIndex!!].labels.remove(title)
                    listLabel.remove("$title $type")
                }
        }
    }

    fun switchTheme(theme: String, content: String) {
        tinyMCE.appearance = Pair(theme, content)
        currentTheme = theme
    }

    fun retrieveNotes(): ObservableList<Note> {
        return observableArrayList(
            Note(0, "Note 1", "NOTE 1 CONTENT~"),
            Note(1, "Note 2", "note 2 content."),
            Note(2, "Note 3", "This is Note 3--")
        )
    }

    fun deleteNote(note: Note) {
        notes.remove(note)
    }
}