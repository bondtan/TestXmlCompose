package com.tbondarenko.testxmlcompose.data.remoteDataSource

import javax.inject.Inject

class RemoteDataSource @Inject constructor( private val emojiApiService: EmojiApiService ){

    suspend fun getRandomEmoji() = emojiApiService.fetchRandomEmoji()

    suspend fun getAllEmoji() =emojiApiService.fetchAllEmoji()

}