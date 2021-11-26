package com.example.obodroid.model.Retrofit.response

import com.google.gson.annotations.SerializedName

data class CoinsResponse (
    @SerializedName("status")
    val status: String = "",
    @SerializedName("data")
    val data: DetailCoins
)

data class DetailCoins(
    @SerializedName("stats")
    val stats: Totals,
    @SerializedName("coins")
    val coins: ArrayList<Coins>

)