package com.yyil.noteapp.entity

import kotlinx.serialization.Serializable

@Serializable
data class TextFormat(
//    var id: String,
    var bold: Boolean = false,
    var italic: Boolean = false,
    var underline: Boolean = false,
    var superscript: Boolean = false,
    var subscript: Boolean = false,
    var font: String = "Helvetica",
    var complexFont: String? = "Helvetica",
    var fontSize: Float = 12.0f,
    var colour: String = "#000000", // RGB for black
    var backgroundColour: String? = null,
    var underlineColour: String? = null,
) {
    val type = "text format"
}
