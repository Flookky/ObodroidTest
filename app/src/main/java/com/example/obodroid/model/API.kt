package com.example.obodroid.model

import com.example.obodroid.model.Retrofit.Interface.CoinsService
import com.example.obodroid.model.Retrofit.response.CoinsResponse
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

object API {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(CoinsService::class.java)


}