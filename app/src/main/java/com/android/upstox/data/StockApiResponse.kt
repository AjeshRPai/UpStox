package com.android.upstox.data


import com.google.gson.annotations.SerializedName

data class StockApiResponse(
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("data")
    val stock: List<StockNetworkModel>,
    @SerializedName("error")
    val error: Any,
    @SerializedName("response_type")
    val responseType: String,
    @SerializedName("timestamp")
    val timestamp: Long
)