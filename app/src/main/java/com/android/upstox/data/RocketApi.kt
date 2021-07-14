package com.android.upstox.data

import io.reactivex.Single
import retrofit2.http.GET

interface StocksApi {

    @GET("v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    fun getStocks(): Single<StockApiResponse>

}