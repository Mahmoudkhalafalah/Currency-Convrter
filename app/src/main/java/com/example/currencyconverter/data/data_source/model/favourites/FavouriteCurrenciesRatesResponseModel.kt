package com.example.currencyconverter.data.data_source.model.favourites

data class FavouriteCurrenciesRatesResponseModel(
    val `data`: List<Double>,
    val status: String,
    val statusCode: Int
)