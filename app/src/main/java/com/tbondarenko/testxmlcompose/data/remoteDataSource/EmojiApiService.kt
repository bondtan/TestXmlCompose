package com.tbondarenko.testxmlcompose.data.remoteDataSource

import retrofit2.Response
import retrofit2.http.GET

interface EmojiApiService {

    /**
     * Get random emoji
     */
    @GET("random")
    suspend fun fetchRandomEmoji(): Response<EmojiNetworkEntity>

    /**
     * Get an array of all emojis
     */
    @GET("all")
    suspend fun fetchAllEmoji(): Response<List<EmojiNetworkEntity>>
}