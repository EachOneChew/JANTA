package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.entity.NoteContentEntity

class Note {
    var id = -1
    var title = ""
    var content = ""

    // type -> name -> id
    var labels: MutableMap<String, MutableMap<String, String>> = mutableMapOf()

    constructor()

    constructor(
        title: String, content: String,
        labels: MutableMap<String, MutableMap<String, String>> = mutableMapOf()) {
        this.title = title
        this.content = content
        this.labels = labels
    }

    fun createNoteContentEntity(): Int {
        var entity = NoteContentEntity(
            noteContent = content, title = title
        )

        return -1
    }
}