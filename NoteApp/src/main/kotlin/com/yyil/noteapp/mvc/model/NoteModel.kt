package com.yyil.noteapp.mvc.model

class NoteModel {

    //val lables = mapOf<>()

    fun insertAnnotation (annotation: String, selection: String): String {
        val openTag = "<span title=\"$annotation\">"
        val closeTag = "</span>"
        var result = selection.replace(">(?=[^<])".toRegex(), ">$openTag")
            .replace("(?<=[^>])<".toRegex(), "$closeTag<")

        if (selection[0] != '<') {
            result = "$openTag$result"
        }

        if (selection[selection.length - 1] != '>') {
            result = "$result$closeTag"
        }

        return result
    }

    fun removeAnnotation (selection: String): String {
        return selection.replace("(<span [^>]+>)".toRegex(), "")
            .replace("(</span>)".toRegex(), "")
    }

}