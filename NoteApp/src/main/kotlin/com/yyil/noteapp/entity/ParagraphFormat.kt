package com.yyil.noteapp.entity

import kotlinx.serialization.Serializable

@Serializable
data class ParagraphFormat (
//    var id: String,
    var align: String = "left",
    var bulletType: String? = null,
    var numberType: String? = null,
    var indentType: String = "tab",
    var indentBefore: Float = 0f,
    var indentAfter: Float = 0f,
    var LineSpacing: Float = 1.5f,
    var LineSpacingBefore: Float = 1.0f, // # of lines
    var LineSpacingAfter: Float = 0f, // # of lines
) {
    val type: String = "paragraph format"
}
