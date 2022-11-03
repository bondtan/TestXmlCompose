package com.tbondarenko.testxmlcompose.domain.repository

import com.tbondarenko.testxmlcompose.core.NetworkResult
import com.tbondarenko.testxmlcompose.data.remoteDataSource.EmojiNetworkEntity
import kotlinx.coroutines.flow.Flow

interface EmojiRepository {

    suspend fun getRandomEmojiRemote(): Flow<NetworkResult<EmojiNetworkEntity>>

    suspend fun getAllEmojiRemote(): Flow<NetworkResult<List<EmojiNetworkEntity>>>

    suspend fun mediatorAllEmojis(): Flow<NetworkResult<List<EmojiNetworkEntity>>>

}