package com.example.currencyconverter.data.data_source.model.currency_exchange

data class CurrencyExchangeModel(
    val base_code: String,
    val conversion_rate: Double,
    val documentation: String,
    val result: String,
    val target_code: String,
    val terms_of_use: String,
    val time_last_update_unix: Int,
    val time_last_update_utc: String,
    val time_next_update_unix: Int,
    val time_next_update_utc: String
)