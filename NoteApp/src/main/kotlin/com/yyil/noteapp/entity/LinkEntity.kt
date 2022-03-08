package com.yyil.noteapp.entity

import kotlinx.serialization.Serializable

@Serializable
data class LinkEntity(
    var contentList: List<ContentEntity> = ArrayList(),
    var templateEntityId: String = "0",
    var textFormat: TextFormat = TextFormat(),
    var link: String? = null,
    var url: String? = null,
) {
    var type: String = "content"
}
