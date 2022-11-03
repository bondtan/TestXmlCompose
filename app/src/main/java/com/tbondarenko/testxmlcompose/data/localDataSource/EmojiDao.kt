package com.tbondarenko.testxmlcompose.data.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmojiDao {

    @Insert(onConflict = REPLACE)
    suspend fun setAllEmojisToDb(emojiLocalEntity: List<EmojiLocalEntity>)

    @Query("SELECT count(id) FROM emojis")
    suspend fun getNumberOfEmojisInDb(): Int

    @Query("SELECT * FROM emojis")
    fun getEmojisFromDb(): Flow<List<EmojiLocalEntity>>
}