package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.TinyMCEInterface
import com.yyil.noteapp.entity.NoteContentEntity
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

                println("------------${notes[newIndex].title}")

                //Save note content when change index
                if (currentIndex != null) {
                    println("Current Idx $currentIndex; newIdx $newIndex")
                    tinyMCE.forceUpdate()
                    saveNoteContent()

                    label = notes[newIndex].labels
                    listLabel.clear()
                    for (key in label.keys) {
                        listLabel.add(key + " " + label[key])
                    }
                }
                //Load note content from DB for the selected note
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

    fun handleLabelNav(newIndex: Int) {
        if (newIndex < notes.size && newIndex >= 0) {
            val title = listLabel[newIndex]
            tinyMCE.navLabel(title)
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
                    listLabel.add("$title")
                }
            "removeLabel" ->
                if (currentIndex != null) {
                    notes[currentIndex!!].labels.remove(title)
                    listLabel.remove("$title")
                }
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

        var noteEntityList = Connect.findNoteFullList(Connect.getConnection(), NoteContentEntity())

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
                if (entity.category != null) {
                    println("did this work?")

                    var nmap = entity.category!!.split(",")
                        .map { it.split("=") }.associate { it.first() to it.last() }

                    println(nmap)
                    note.labels = nmap as MutableMap<String, String>
                }
                else {
                    println("it did not work")
                    note.labels = mutableMapOf()
                }
                noteList.add(note)
                //println("Adding from DB ------------------ ${note.id}, ${note.title}")
            }
        }
        return noteList
    }

    fun deleteNote(note: Note) {
        notes.remove(note)
        if (Connect.deleteNoteById(Connect.getConnection(), note.id) == 0) {
            System.err.println("Note not found in DB")
        }
        tinyMCE.content = ""
        currentIndex = null
    }

    fun saveNoteContent() {
        if (currentIndex == null || currentIndex!! < 0) {
            System.err.println("No note is selected")
            return
        }
        tinyMCE.forceUpdate()

        var mapAsString : String? = null

        if (label.keys.isNotEmpty()) {
            val nString = StringBuilder()
            for (key in label.keys) {
                nString.append(key + "=" + label[key] + ",")
            }
            nString.setLength(nString.length - 1)
            mapAsString = nString.toString()
        }

        println("here")
        println(mapAsString)

        var entity = NoteContentEntity(noteContentId = notes[currentIndex!!].id, noteContent = tinyMCE.content, category = mapAsString)
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
}