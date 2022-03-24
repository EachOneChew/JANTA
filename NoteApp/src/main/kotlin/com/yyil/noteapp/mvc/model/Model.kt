package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.TinyMCEInterface
import com.yyil.noteapp.entity.NoteContentEntity
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import java.time.LocalDateTime

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

    val connect = Connect.getConnection()
    var success = false

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
     * Label args example: ["definition", "Theorem 3.3.1"] a.k.a. [TYPE, TITLE]
     */
    fun handleModelCall(target: String, type: String, title: String) {
        when (target) {
//            "label" -> if (currentIndex != null) {
//                if (notes[currentIndex!!].labels.containsKey<String>(arg[0])){
//                    notes[currentIndex!!].labels[arg[0]]?.put(arg[1], arg[2]) // Will overwrite if the name already exists
//                }
//                else {
//                    val nmap = mutableMapOf<String, String>()
//                    nmap[arg[1]] = arg[2]
//                    notes[currentIndex!!].labels[arg[0]] = nmap
//                }
//            }
        }
    }

    fun switchTheme(theme: String, content: String) {
        tinyMCE.appearance = Pair(theme, content)
        currentTheme = theme
    }

    fun retrieveNotes(): ObservableList<Note> {

        var note1 = Note("Note 1", "NOTE 1 CONTENT~")
        var note2 = Note("Note 2", "note 2 content.")
        var note3 = Note("Note 3", "This is Note 3--")
        //success = Connect.create(connect, note1.createNoteContentEntity())
        //println("Create Note success: $success")

        return FXCollections.observableArrayList(
            note1,
            note2,
            note3
        )
    }
/*
    fun retrieveLabels(): MutableSet<String> {
        if (currentIndex != null) {
            return notes[currentIndex!!].labels.keys
        }
        return mutableSetOf()
    }

 */

    fun deleteNote(id: Int) {
        for (note in notes) {
            if (note.id == id) {
                notes.remove(note)
                break
            }
        }
    }
}