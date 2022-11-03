package com.tbondarenko.testxmlcompose.data.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EmojiLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EmojisDatabase: RoomDatabase() {

    abstract fun getEmojiDao(): EmojiDao
}