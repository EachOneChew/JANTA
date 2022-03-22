package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.NoteCell
import com.yyil.noteapp.mvc.view.NoteRepository
import javafx.event.EventHandler

class NoteCellController(
    val model: Model,
    noteRepository: NoteRepository,
    noteCell: NoteCell
) {
    init {
        noteCell.deleteOption.onAction = EventHandler {
            model.deleteNote(noteCell.note.id)
            for (note in noteRepository.noteList.items) {
                if (note.id == noteCell.note.id) {
                    noteRepository.noteList.items.remove(note)
                    break
                }
            }
            println("Deleted note ${noteCell.note.title}; ID: ${noteCell.note.id}")
        }
    }
}