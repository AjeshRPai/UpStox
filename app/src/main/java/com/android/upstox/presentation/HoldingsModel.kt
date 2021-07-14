package com.android.upstox.presentation

import java.math.BigDecimal

data class StockModel(
    val name: String,
    val quantity: Int,
    val ltp: BigDecimal,
    val profitOrLoss: BigDecimal,
    val isT1Holding: Boolean = false
)

data class HoldingValue(
    val currentValue: BigDecimal,
    val totalInvestment: BigDecimal,
    val totalProfitAndLoss:BigDecimal,
    val todayProfitAndLoss:BigDecimal
)


data class Holdings(
    val list: List<StockModel>,
    val holding:HoldingValue
)


