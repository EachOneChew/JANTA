package com.yyil.noteapp.mvc.model

class Note(val index: Int, var title: String) {
    var content = ""

    // name -> type
    var labels: MutableMap<String, String> = mutableMapOf()

    constructor(
        index: Int, title: String, content: String,
        labels: MutableMap<String, String> = mutableMapOf()) : this(index, title) {
        this.content = content
        this.labels = labels
    }
}