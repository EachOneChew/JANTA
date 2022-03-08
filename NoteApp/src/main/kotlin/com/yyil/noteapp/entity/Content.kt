package com.yyil.noteapp.entity

import kotlinx.serialization.Serializable

@Serializable
open class Content(
    var type: String = "content",
    var contentList: List<ContentEntity> = ArrayList()
)
