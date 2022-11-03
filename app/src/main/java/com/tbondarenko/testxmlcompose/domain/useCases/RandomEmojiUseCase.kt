package com.tbondarenko.testxmlcompose.domain.useCases

import android.util.Log
import com.tbondarenko.testxmlcompose.core.NetworkResult
import com.tbondarenko.testxmlcompose.data.remoteDataSource.EmojiNetworkEntity
import com.tbondarenko.testxmlcompose.domain.repository.EmojiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RandomEmojiUseCase @Inject constructor(
    private val repository: EmojiRepository
): UseCase<NetworkResult<EmojiNetworkEntity>> {

    override suspend operator fun invoke(): Flow<NetworkResult<EmojiNetworkEntity>> {
        Log.d("RandUC","${repository.getRandomEmojiRemote()}")
        return repository.getRandomEmojiRemote()
    }
}