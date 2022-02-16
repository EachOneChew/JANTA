package com.yyil.noteapp.entity

import kotlinx.serialization.*
import kotlinx.serialization.json.*
// global for user

@Serializable
data class FileInfo (
    val type: String = "file_info",
    val id: String,

    val createdBy: String,
    val createdTime: String? = null,
    val editedBy: String? = null,
    val editedTime: String? = null,

    val indentType: String? = null,
    val fileEncoding: String? = null,
    val lineSeparator: String = "\n",
    val children: MutableList<FileInfo>? = ArrayList()
)

data class TemplateEntity (
    val basedOn: String? = null,
    val align: String? = null,
    val bold: String? = "false",
    val font: String? = null,
    val fontSize: Long? = null,
    val type: String? = null,
)

abstract class Content (
    val name: String? = null,
    val children: List<ContentEntity>? = null
)

open class ContentEntity (
    val text: String? = null,
    val bold: String? = null,
    val link: String? = null,
    val type: String? = null,
    val children: List<ContentEntity>? = null
)

data class LinkEntity (
    val url: String? = null,
): ContentEntity()

data class ImageEntity (
    val src: String? = null,
    val option: String? = null
)
