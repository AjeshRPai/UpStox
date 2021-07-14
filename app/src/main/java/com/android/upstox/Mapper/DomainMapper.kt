package com.android.upstox.Mapper

import com.android.upstox.data.StockNetworkModel
import com.android.upstox.presentation.StockModel
import java.math.BigDecimal

interface DomainMapper<in T, out A> {
    fun map(model: T): A
}

class StockDomainMapper : DomainMapper<StockNetworkModel, StockModel> {

    override fun map(model: StockNetworkModel): StockModel {
        return convert(model)
    }

    private fun convert(stockModel: StockNetworkModel): StockModel {
        with(stockModel) {
            return StockModel(
                name = symbol,
                quantity = holdingsUpdateQty,
                ltp = BigDecimal.valueOf(ltp),
                profitOrLoss = BigDecimal(holdingsUpdateQty).multiply(BigDecimal.valueOf(close).subtract(BigDecimal.valueOf(ltp))),
                isT1Holding = t1HoldingQty !=0

            )
        }
    }

//    1. Current value = sum of (Last traded price * quantity of holding ) of all the holdings
//    2. Total investment = sum of (Average Price * quantity of holding ) of all the holdings
//    3. Total PNL = Current value - Total Investment
//    4. Today’s PNL = sum of ((Close - LTP ) * quantity) of all the holdings

}