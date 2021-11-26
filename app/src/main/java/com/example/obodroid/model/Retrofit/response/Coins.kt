package com.example.obodroid.model.Retrofit.response

import com.google.gson.annotations.SerializedName

data class Coins(
    @SerializedName("uuid")
    val uuid: String = "",
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("color")
    val color: String = "",
    @SerializedName("iconUrl")
    val iconUrl: String = "",
    @SerializedName("marketCap")
    val marketCap: String = "",
    @SerializedName("price")
    val price: String = "",
    @SerializedName("listedAt")
    val listedAt: String = "",
    @SerializedName("tier")
    val tier: String = "",
    @SerializedName("change")
    val change: String = "",
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("sparkline")
    val sparkLine: ArrayList<String>,
    @SerializedName("lowVolume")
    val lowVolume: Boolean,
    @SerializedName("coinrankingUrl")
    val coinRankingUrl: String = "",
    @SerializedName("24hVolume")
    val volume24h: String = "",
    @SerializedName("btcPrice")
    val btcPrice: String = "",

)