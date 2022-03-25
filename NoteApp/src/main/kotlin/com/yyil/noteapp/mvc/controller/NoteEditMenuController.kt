package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteEditMenu
import com.yyil.noteapp.mvc.view.NoteRepository
import javafx.event.EventHandler

class NoteEditMenuController(
    var model: Model,
    noteEditMenu: NoteEditMenu,
    noteRepository: NoteRepository
) {
    init {

        noteEditMenu.noteCell.emptyProperty().addListener { _, _, isEmpty ->
            if(isEmpty){
                noteEditMenu.noteCell.contextMenu = null
            }else{
                noteEditMenu.noteCell.contextMenu = noteEditMenu
            }
        }

        noteEditMenu.deleteOption.onAction = EventHandler {
            println("Deleted note ${noteEditMenu.noteCell.note.title};" +
                    " ID: ${noteEditMenu.noteCell.note.id}")
            model.deleteNote(noteEditMenu.noteCell.note)
        }

        noteEditMenu.renameOption.onAction = EventHandler {
            println("Rename note ${noteEditMenu.noteCell.note.title};" +
                    " ID: ${noteEditMenu.noteCell.note.id}")

            noteRepository.renameDialog.showAndWait()
            model.updateNoteTitle(noteEditMenu.noteCell.note, noteRepository.renameDialog.editor.text)
            noteRepository.noteList.refresh()

            println(" To ${noteEditMenu.noteCell.note.title}")
        }
    }
}