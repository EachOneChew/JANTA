package com.yyil.noteapp.mvc.model

class Note(val index: Int, var title: String) {
    var content = ""

    constructor(index: Int, title: String, content: String) : this(index, title) {
        this.content = content
    }
}