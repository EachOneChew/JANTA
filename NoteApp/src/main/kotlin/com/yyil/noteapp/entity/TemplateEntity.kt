package com.yyil.noteapp.entity

import kotlinx.serialization.Serializable

@Serializable
data class TemplateEntity (
    var id: String,

    var basedOn: String? = null,
    var align: String? = null,
    var textFormat: TextFormat = TextFormat(),
    var paragraphFormat: ParagraphFormat = ParagraphFormat()
) {
    val type:String = "templateEntity"
}
