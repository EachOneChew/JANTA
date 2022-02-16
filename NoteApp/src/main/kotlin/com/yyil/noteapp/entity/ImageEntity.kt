package com.yyil.noteapp.entity

import kotlinx.serialization.Serializable

@Serializable
data class ImageEntity (
    var contentList: List<ContentEntity> = ArrayList(),
    var text: String? = null,
    var templateEntityId: String = "0",
    var textFormat: TextFormat = TextFormat(),
    var src: String? = null,
    var option: String? = null
) {
    var type: String = "content"
}
