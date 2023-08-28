package com.example.currencyconverter.data.data_source.model.currencies

data class SupportedCurrenciesResponseModel(
    val `data`: List<Data>,
    val statusCode: Int
)