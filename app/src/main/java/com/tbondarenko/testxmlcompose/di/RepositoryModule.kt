package com.tbondarenko.testxmlcompose.di

import com.tbondarenko.testxmlcompose.data.repository.EmojiRepositoryImpl
import com.tbondarenko.testxmlcompose.domain.repository.EmojiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This module provides instances for Repository
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repository: EmojiRepositoryImpl
    ): EmojiRepository
}
