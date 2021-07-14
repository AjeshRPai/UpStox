package com.android.upstox.data
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class StockNetworkModel(
    @SerializedName("avg_price")
    val avgPrice: String,
    @SerializedName("close")
    val close: Double,
    @SerializedName("cnc_used_quantity")
    val cncUsedQuantity: Int,
    @SerializedName("collateral_qty")
    val collateralQty: Int,
    @SerializedName("collateral_type")
    val collateralType: String,
    @SerializedName("collateral_update_qty")
    val collateralUpdateQty: Int,
    @SerializedName("company_name")
    val companyName: String,
    @SerializedName("haircut")
    val haircut: Double,
    @SerializedName("holdings_update_qty")
    val holdingsUpdateQty: Int,
    @SerializedName("isin")
    val isin: String,
    @SerializedName("ltp")
    val ltp: Double,
    @SerializedName("product")
    val product: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("t1_holding_qty")
    val t1HoldingQty: Int,
    @SerializedName("token_bse")
    val tokenBse: String,
    @SerializedName("token_nse")
    val tokenNse: String,
    @SerializedName("withheld_collateral_qty")
    val withheldCollateralQty: Int,
    @SerializedName("withheld_holding_qty")
    val withheldHoldingQty: Int
)