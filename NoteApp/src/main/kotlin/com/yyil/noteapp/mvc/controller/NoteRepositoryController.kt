package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteCell
import com.yyil.noteapp.mvc.view.NotePlaceHolderCell
import com.yyil.noteapp.mvc.view.NoteRepository
import javafx.event.EventHandler

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

        noteRepository.noteList.items.addAll(model.notes)

        noteRepository.noteList.placeholder = NotePlaceHolderCell()

        noteRepository.noteList.setCellFactory {

            var noteCell = NoteCell()
            println("Created note cell ${noteCell.note.title}; ID: ${noteCell.note.id}")

            noteCell.deleteOption.onAction = EventHandler {
                println("Deleted note ${noteCell.note.title}; ID: ${noteCell.note.id}")
                model.notes.remove(noteCell.note)
                noteRepository.noteList.items.remove(noteCell.note)
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

