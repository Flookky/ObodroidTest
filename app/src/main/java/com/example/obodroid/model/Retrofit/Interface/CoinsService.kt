package com.example.obodroid.model.Retrofit.Interface

import com.example.obodroid.model.Retrofit.response.CoinsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface CoinsService {
    @GET("https://api.coinranking.com/v2/coins")
    suspend fun getCoins(): Response<CoinsResponse>
}