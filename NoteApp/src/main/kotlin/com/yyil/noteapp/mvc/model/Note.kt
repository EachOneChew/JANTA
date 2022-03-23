package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.entity.NoteContentEntity

class Note {
    var id = -1
    var title = ""
    var content = ""

    constructor(title: String, content: String) {
        this.title = title
        this.content = content
    }

    fun createNoteContentEntity(): Int {
        var entity = NoteContentEntity(
            noteContent = content, title = title
        )

        return -1
    }
}