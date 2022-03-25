package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.entity.NoteContentEntity

class Note {
    var id = -1
    var title = ""
    //var content = ""

    // type -> name -> id
    var labels: MutableMap<String, MutableMap<String, String>> = mutableMapOf()

    constructor()

    constructor(id: Int, title: String){
        this.id = id
        this.title = title
    }

    constructor(
        id: Int,
        title: String,
        labels: MutableMap<String, MutableMap<String, String>> = mutableMapOf()
    ) {
        this.id = id
        this.title = title
        this.labels = labels
    }

//    fun noteEntity(): NoteContentEntity {
//        return NoteContentEntity(
//            noteContent = content, title = title
//        )
//    }
}