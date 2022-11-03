package com.tbondarenko.testxmlcompose.di

import com.squareup.moshi.Moshi
import com.tbondarenko.testxmlcompose.data.remoteDataSource.EmojiApiService
import com.tbondarenko.testxmlcompose.data.remoteDataSource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * This module provides instances for library network classes:
 * - Moshi
 * - OkHttpClient
 * - Retrofit
 * - LoggingInterceptor
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesBaseUrl(): String =
        "https://emojihub.herokuapp.com/api/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):
            OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideEmojiApi(retrofit: Retrofit): EmojiApiService {
        return retrofit.create(EmojiApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(emojiApiService: EmojiApiService):
           RemoteDataSource {
        return RemoteDataSource(emojiApiService)
    }
}