package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteCell
import com.yyil.noteapp.mvc.view.NotePlaceHolderCell
import com.yyil.noteapp.mvc.view.NoteRepository
import javafx.event.EventHandler
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem

class NoteRepositoryController(
    val model: Model,
    noteRepository: NoteRepository
) {
    init {
        noteRepository.noteList.onMouseClicked = EventHandler {
            val i = noteRepository.noteList.selectionModel.selectedIndex
            model.handleNoteSelect(i)
            println("Selected note $i")
        }

        noteRepository.noteList.items = model.notes

        noteRepository.noteList.placeholder = NotePlaceHolderCell()

        noteRepository.noteList.setCellFactory {

            var noteCell = NoteCell()
            println("Created note cell ${noteCell.note.title}; ID: ${noteCell.note.id}")
            var contextMenu = ContextMenu()
            var renameOption = MenuItem("Rename")
            var deleteOption = MenuItem("Delete")
            contextMenu.items.addAll(renameOption, deleteOption)
            
            noteCell.emptyProperty().addListener { observable, wasEmpty, isEmpty ->
                if(isEmpty){
                    noteCell.contextMenu = null
                }else{
                    noteCell.contextMenu = contextMenu
                }
            }

            deleteOption.onAction = EventHandler {
                println("Deleted note ${noteCell.note.title}; ID: ${noteCell.note.id}")
                model.notes.remove(noteCell.note)
            }

            renameOption.onAction = EventHandler {
                println("Rename note ${noteCell.note.title}; ID: ${noteCell.note.id}")
                noteRepository.renameDialog.showAndWait()
                noteCell.note.title = noteRepository.renameDialog.editor.text
                noteRepository.noteList.refresh()
                println(" To ${noteCell.note.title}")
            }

            noteCell
        }
        /*
        noteRepository.noteList.onMouseClicked = EventHandler<MouseEvent> { click ->
            if (click.isSecondaryButtonDown) {
                val selectedNote = noteRepository.noteList.selectionModel.selectedItem

            }
        }

         */
    }
}

