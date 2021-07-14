package com.android.upstox.data

import com.android.upstox.Mapper.StockDomainMapper
import com.android.upstox.presentation.StockModel
import io.reactivex.Single
import javax.inject.Inject

class StockRepository @Inject constructor(
    private val stocksApi: StocksApi,
    private val stockDomainMapper: StockDomainMapper,
) {

    fun getStocks(): Single<Result<List<StockModel>>> {
        return getStocksFromApi()
            .onErrorResumeNext { throwable ->
                return@onErrorResumeNext Single.error(throwable)
            }
            .map { list ->
                if (list.isNotEmpty()) mapToDomainResult(list)
                else Result.failure(Throwable("No Results"))
            }
            .onErrorReturn { Result.failure(it) }
    }

    private fun mapToDomainResult(list: List<StockNetworkModel>): Result<List<StockModel>> =
        Result.success(list.map { stockDomainMapper.map(it) })


    private fun getStocksFromApi(): Single<List<StockNetworkModel>> {
        return stocksApi.getStocks().map { it.stock }
    }


}

