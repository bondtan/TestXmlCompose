package com.tbondarenko.testxmlcompose.data.remoteDataSource

import com.squareup.moshi.Json
import com.tbondarenko.testxmlcompose.data.localDataSource.EmojiLocalEntity
import com.tbondarenko.testxmlcompose.domain.models.EmojiDomain

/**
 * {
 * name: "hugging face",
 * category: "smileys and people",
 * group: "face positive",
 * htmlCode: [
 *  "&#129303;"
 *  ],
 * unicode: [
 * "U+1F917"
 *  ]
 * }
 */

data class EmojiNetworkEntity(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "category") val category: String,
    @field:Json(name = "group") val group: String,
    @field:Json(name = "htmlCode") val htmlCode: List<String>,
    @field:Json(name = "unicode") val unicode: List<String>,
)

fun EmojiNetworkEntity.toDataDomain(): EmojiDomain =
    EmojiDomain(
        name = this.name.substringBefore(","),
        category = this.category,
        group = this.group,
        html = this.htmlCode.first()
    )

fun EmojiNetworkEntity.toLocalEntity(): EmojiLocalEntity =
    EmojiLocalEntity(
        name = this.name,
        category = this.category,
        group = this.group,
        html = this.htmlCode.first(),
        unicode = this.unicode.first()
    )
