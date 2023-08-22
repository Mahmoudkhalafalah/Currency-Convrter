package com.example.currencyconverter.data.data_source.model.currency_historical_data

data class CurrencyHistoricalDataModel(
    val base_code: String,
    val conversion_rates: ConversionRates,
    val day: Int,
    val documentation: String,
    val month: Int,
    val result: String,
    val terms_of_use: String,
    val year: Int
)