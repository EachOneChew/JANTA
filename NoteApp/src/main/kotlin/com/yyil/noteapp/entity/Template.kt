package com.yyil.noteapp.entity

import kotlinx.serialization.*

@Serializable
class Template (
    var templateEntityList: List<TemplateEntity>? = ArrayList()
) {
    val type: String = "template"
}
