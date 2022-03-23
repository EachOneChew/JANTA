package com.yyil.noteapp.mvc.model

class Note(val index: Int, var title: String) {
    var content = ""

    // type -> name -> id
    var labels: MutableMap<String, MutableMap<String, String>> = mutableMapOf()

    constructor(
        index: Int, title: String, content: String,
        labels: MutableMap<String, MutableMap<String, String>> = mutableMapOf()) : this(index, title) {
        this.content = content
        this.labels = labels
    }
}