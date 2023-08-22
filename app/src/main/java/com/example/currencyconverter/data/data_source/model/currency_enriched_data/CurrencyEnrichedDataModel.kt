package com.example.currencyconverter.data.data_source.model.currency_enriched_data

data class CurrencyEnrichedDataModel(
    val base_code: String,
    val conversion_rate: Double,
    val documentation: String,
    val result: String,
    val target_code: String,
    val target_data: TargetData,
    val terms_of_use: String,
    val time_last_update_unix: Int,
    val time_last_update_utc: String,
    val time_next_update_unix: Int,
    val time_next_update_utc: String
)