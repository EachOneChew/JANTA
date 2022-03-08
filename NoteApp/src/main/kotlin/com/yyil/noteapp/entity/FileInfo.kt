package com.yyil.noteapp.entity

import kotlinx.serialization.Serializable

@Serializable
data class FileInfo(
    var id: String,
    var createdBy: String,
    var createdTime: String,
    var editedBy: String? = null,
    var editedTime: String? = null,

    var indentType: String = "tab",
    var fileEncoding: String? = null,
    var lineSeparator: String = "\n",
    var templateList: MutableList<Template>? = ArrayList()
) {
    val type: String = "file_info"
}


