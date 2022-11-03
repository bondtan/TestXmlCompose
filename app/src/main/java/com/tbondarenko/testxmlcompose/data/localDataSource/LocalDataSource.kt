package com.tbondarenko.testxmlcompose.data.localDataSource

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val emojiDao: EmojiDao
){

    fun getAllEmojisFromDb(): Flow<List<EmojiLocalEntity>> =
         emojiDao.getEmojisFromDb()

    suspend fun getNumberOfEmojisInDb(): Int =
        emojiDao.getNumberOfEmojisInDb()

    suspend fun setAllEmojisToDb(emojis: List<EmojiLocalEntity>) =
        emojiDao.setAllEmojisToDb(emojis)
}