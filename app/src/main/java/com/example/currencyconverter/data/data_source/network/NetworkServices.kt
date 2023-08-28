package com.example.currencyconverter.data.data_source.network

import com.example.currencyconverter.data.data_source.model.comparison.CurrenciesComparisonResponseModel
import com.example.currencyconverter.data.data_source.model.currencies.SupportedCurrenciesResponseModel
import com.example.currencyconverter.data.data_source.model.favourites.FavouriteCurrenciesRatesResponseModel
import com.example.currencyconverter.data.data_source.model.pair_conversion.PairConversionResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkServices {

    @GET("/pair-conversion")
    suspend fun getExchangeAmount(
        @Query("base") base: String,
        @Query("target") target: String,
        @Query("amount") amount: Double,
    ):PairConversionResponseModel

    @GET("/comparison")
    suspend fun getExchangeAmountComparison(
        @Query("base") base: String,
        @Query("target1") firstTarget: String,
        @Query("target2") secondTarget: String,
        @Query("amount") amount: Double,
    ): CurrenciesComparisonResponseModel

    @POST("favorite-currencies")
    suspend fun getFavouriteCurrenciesExchangeRate(
        @Query("base") base: String,
        @Body currenciesList: List<String>,
    ): FavouriteCurrenciesRatesResponseModel

    @GET("currencies")
    suspend fun getAllSupportedCurrencies():SupportedCurrenciesResponseModel

}