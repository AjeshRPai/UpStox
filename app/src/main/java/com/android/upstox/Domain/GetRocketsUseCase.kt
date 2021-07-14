package com.android.upstox


import com.android.upstox.data.StockRepository
import com.android.upstox.presentation.StockModel
import io.reactivex.Single
import javax.inject.Inject

interface GetStocksUseCase {
    operator fun invoke(): Single<Result<List<StockModel>>>
}

class GetStocksUseCaseImpl @Inject constructor(
    private val repository: StockRepository
): GetStocksUseCase {
    override fun invoke(): Single<Result<List<StockModel>>> {
        return repository.getStocks()
    }
}



