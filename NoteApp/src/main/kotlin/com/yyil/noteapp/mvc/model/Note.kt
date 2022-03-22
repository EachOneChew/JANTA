package com.yyil.noteapp.mvc.model

class Note(val id: Int, var title: String) {
    var content = ""

    constructor(id: Int, title: String, content: String) : this(id, title) {
        this.content = content
    }
}