package com.tbondarenko.testxmlcompose.data.localDataSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tbondarenko.testxmlcompose.data.remoteDataSource.EmojiNetworkEntity
import com.tbondarenko.testxmlcompose.domain.models.EmojiDomain

@Entity(tableName = "emojis")
data class EmojiLocalEntity (
    @PrimaryKey(autoGenerate = true)val id: Int? = null,
    val name: String,
    val category: String,
    val group: String,
    val html: String,
    val unicode: String
    )

fun EmojiLocalEntity.toDataDomain(): EmojiDomain =
    EmojiDomain(
        name = this.name,
        category = this.category,
        group = this.group,
        html = this.html
    )

fun EmojiLocalEntity.toDataNetworkEntity(): EmojiNetworkEntity =
    EmojiNetworkEntity(
        name = this.name,
        category = this.category,
        group = this.group,
        htmlCode = listOf(this.html),
        unicode = listOf(this.unicode)
    )