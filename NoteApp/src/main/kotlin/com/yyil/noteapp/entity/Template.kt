package com.yyil.noteapp.entity

@Serializable
abstract class Template (
    val name: String? = null,
    val indent: Long? = null,
    val children: List<TemplateEntity>? = null
)
