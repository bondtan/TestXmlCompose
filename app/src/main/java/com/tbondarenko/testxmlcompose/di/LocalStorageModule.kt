package com.tbondarenko.testxmlcompose.di

import android.content.Context
import androidx.room.Room
import com.tbondarenko.testxmlcompose.data.localDataSource.EmojiDao
import com.tbondarenko.testxmlcompose.data.localDataSource.EmojisDatabase
import com.tbondarenko.testxmlcompose.data.localDataSource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {

    @Provides
    @Singleton
    fun provideEmojisDatabase(
        @ApplicationContext app: Context
    ): EmojisDatabase {
        return Room.databaseBuilder(
            app,
            EmojisDatabase::class.java,
            "emojis"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEmojiDao(db: EmojisDatabase): EmojiDao {
        return  db.getEmojiDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource( dao: EmojiDao ): LocalDataSource {
        return LocalDataSource(dao)
    }
}