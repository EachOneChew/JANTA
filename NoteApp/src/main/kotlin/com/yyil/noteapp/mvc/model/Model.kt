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
                    tinyMCE.forceUpdate()
                    println("Current Idx $currentIndex; newIdx $newIndex")
                    notes[currentIndex!!].content = tinyMCE.content
                }
                */
                var entity = Connect.findNoteById(Connect.getConnection(), notes[newIndex].id)
                if (entity != null) {
                    tinyMCE.content = entity.noteContent!!
                }else{
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

        var noteEntityList = FXCollections.observableArrayList(
            NoteContentEntity(title = "Note 1", noteContent = "NOTE 1 CONTENT~"),
            NoteContentEntity(title = "Note 2", noteContent = "note 2 content."),
            NoteContentEntity(title = "Note 3", noteContent = "This is Note 3--")
        )
        var noteList = FXCollections.observableArrayList<Note>()

        for (note in noteEntityList){
            var id = Connect.create(Connect.getConnection(), note)
            if (note.title != null) {
                noteList.add(Note(id, note.title!!))
            }else{
                noteList.add(Note(id, ""))
            }
        }
        return noteList
    }

    fun deleteNote(note: Note) {
//        for (note in notes) {
//            if (note.id == id) {
//                notes.remove(note)
//                break
//            }
//        }

        notes.remove(note)
        if(Connect.deleteNoteById(Connect.getConnection(), note.id) == 0){
            println("Note not found in DB")
        }
    }

    fun updateNoteContent(){
        if(currentIndex == null || currentIndex!! < 0){
            println("No note is selected")
            return
        }
        var entity = NoteContentEntity(noteContentId = notes[currentIndex!!].id, noteContent = tinyMCE.content)
        Connect.update(Connect.getConnection(), entity)
    }

    fun updateNoteTitle(note: Note, title: String){
        note.title = title
        var entity = NoteContentEntity(noteContentId = note.id, title = title)
        Connect.update(Connect.getConnection(), entity)
    }
}