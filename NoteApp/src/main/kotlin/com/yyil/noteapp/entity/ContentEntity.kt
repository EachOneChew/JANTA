package com.yyil.noteapp.entity

import kotlinx.serialization.Serializable

@Serializable
open class ContentEntity  (
    open var type: String = "content",
    open var contentList: List<ContentEntity> = ArrayList(),
    open var text: String? = null,
    open var templateEntityId: String = "0",
    open var textFormat: TextFormat = TextFormat()
)
