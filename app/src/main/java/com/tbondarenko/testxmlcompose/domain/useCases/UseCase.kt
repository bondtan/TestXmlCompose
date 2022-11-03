package com.tbondarenko.testxmlcompose.domain.useCases

import kotlinx.coroutines.flow.Flow

interface UseCase<T> {

    suspend fun invoke(): Flow<T>
}