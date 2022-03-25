package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.TinyMCEInterface
import com.yyil.noteapp.entity.NoteContentEntity
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
                    println("Current Idx $currentIndex; newIdx $newIndex")
                    saveNoteContent()
                }
                */

                var entity = Connect.findNoteById(Connect.getConnection(), notes[newIndex].id)
                if (entity == null) {
                    System.err.println("Note not found: ${notes[newIndex].id}")
                    return
                }
                if (entity!!.noteContent != null) {
                    tinyMCE.content = entity.noteContent!!
                } else {
                    tinyMCE.content = ""
                    println("No content for ${notes[newIndex].title}")
                }
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

    fun retrieveLabels(): MutableSet<String> {
        if (currentIndex != null) {
            return notes[currentIndex!!].labels.keys
        }
        return mutableSetOf()
    }

    fun retrieveNotes(): ObservableList<Note> {

        var noteList = FXCollections.observableArrayList<Note>()

        var noteEntityList = Connect.findNoteTitleList(Connect.getConnection(), NoteContentEntity())

        if (noteEntityList != null) {
            for (entity in noteEntityList) {
                var id = entity.noteContentId
                if (id == null) {
                    System.err.println("Note ID is null, note title: ${entity.title}")
                    continue
                }
                var note = Note()
                note.id = id
                if (entity.title != null) {
                    note.title = entity.title!!
                } else {
                    note.title = ""
                }
                noteList.add(note)
                println("Adding from DB ------------------ ${note.id}, ${note.title}")
            }
        }
        return noteList
    }

    fun deleteNote(note: Note) {
        notes.remove(note)
        if (Connect.deleteNoteById(Connect.getConnection(), note.id) == 0) {
            System.err.println("Note not found in DB")
        }
    }

    fun saveNoteContent() {
        if (currentIndex == null || currentIndex!! < 0) {
            System.err.println("No note is selected")
            return
        }
        tinyMCE.forceUpdate()
        var entity = NoteContentEntity(noteContentId = notes[currentIndex!!].id, noteContent = tinyMCE.content)
        Connect.update(Connect.getConnection(), entity)
    }

    fun updateNoteTitle(note: Note, title: String) {
        note.title = title
        var entity = NoteContentEntity(noteContentId = note.id, title = title)
        Connect.update(Connect.getConnection(), entity)
    }

    fun addNote(title: String) {
        var id = Connect.create(Connect.getConnection(), NoteContentEntity(title = title, noteContent = ""))
        notes.add(Note(id, title))
    }
}