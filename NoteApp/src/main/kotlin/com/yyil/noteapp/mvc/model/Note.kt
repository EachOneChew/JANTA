package com.yyil.noteapp.mvc.model

class Note {
    var id = -1
    var title = ""

    // name -> type
    var labels: MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id: Int, title: String) {
        this.id = id
        this.title = title
    }

    constructor(
        id: Int,
        title: String,
        labels: MutableMap<String, String> = mutableMapOf()
    ) {
        this.id = id
        this.title = title
        this.labels = labels
    }
}