package com.yyil.noteapp.mvc.model

class NoteModel {

    //val lables = mapOf<>()

    fun insertAnnotation (annotation: String, selection: String): String {
        var inTag = false
        var startTag = false
        var endTag = false
        val openTag = "<span title=\"$annotation\">"
        val closeTag = "</span>"
        var result = ""

        if (selection[0] != '<') {
            result += openTag
        }

        for (letter in selection) {

            if (letter == '<') {
                inTag = true
                startTag = false

                if (endTag) {
                    result += closeTag
                    endTag = false
                }
            }

            if (startTag) {
                result += openTag
                startTag = false
            }

            result += letter

            if (!inTag) {
                endTag = true
            }

            else if (letter == '>') {
                inTag = false
                startTag = true
            }
        }

        if (selection[selection.length - 1] != '>') {
            result += closeTag
        }

        return result
    }

    fun removeAnnotation (selection: String) {
        var result = ""
    }

}