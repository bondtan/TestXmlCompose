package com.tbondarenko.testxmlcompose.data.repository

import com.tbondarenko.testxmlcompose.core.NetworkResult
import com.tbondarenko.testxmlcompose.data.localDataSource.EmojiLocalEntity
import com.tbondarenko.testxmlcompose.data.localDataSource.LocalDataSource
import com.tbondarenko.testxmlcompose.data.localDataSource.toDataNetworkEntity
import com.tbondarenko.testxmlcompose.data.remoteDataSource.*
import com.tbondarenko.testxmlcompose.di.IoDispatcher
import com.tbondarenko.testxmlcompose.domain.repository.EmojiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmojiRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseRepository(), EmojiRepository {

    override suspend fun getRandomEmojiRemote(): Flow<NetworkResult<EmojiNetworkEntity>> =
        flow {
            emit(safeApiCall {
                remoteDataSource.getRandomEmoji()
            })
        }.flowOn(ioDispatcher)

    override suspend fun mediatorAllEmojis(): Flow<NetworkResult<List<EmojiNetworkEntity>>> {
        return if (localDataSource.getNumberOfEmojisInDb() != 0) {
            getAllEmojiLocal().map { list ->
                NetworkResult.Success(data =list.map { it.toDataNetworkEntity()})
            }.flowOn(ioDispatcher)
        } else
         getAllEmojiRemote().flowOn(ioDispatcher)
    }

    override suspend fun getAllEmojiRemote(): Flow<NetworkResult<List<EmojiNetworkEntity>>> =
        flow {
            emit(safeApiCall(::saveEmojisToDb) {
                remoteDataSource.getAllEmoji()
            })
        }.flowOn(ioDispatcher)

    private fun getAllEmojiLocal(): Flow<List<EmojiLocalEntity>> =
        localDataSource.getAllEmojisFromDb()

    private suspend fun saveEmojisToDb(emojisNetworkEntity: List<EmojiNetworkEntity>) {
        val localData = emojisNetworkEntity.map { it.toLocalEntity() }
        localDataSource.setAllEmojisToDb(localData)
    }
}