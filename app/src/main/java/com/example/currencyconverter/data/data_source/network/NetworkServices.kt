package com.example.currencyconverter.data.data_source.network

import com.example.currencyconverter.data.data_source.model.comparison.ComparisonResponseModel
import com.example.currencyconverter.data.data_source.model.pair_conversion.PairExchangeResponseModel
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
    ):

    @GET("/comparison")
    suspend fun getExchangeAmountComparison(
        @Query("base") base: String,
        @Query("target1") firstTarget: String,
        @Query("target") secondTarget: String,
        @Query("amount") amount: Double,
    ): ComparisonResponseModel

    @POST("favorite-currencies")
    suspend fun getFavouriteCurrenciesExchangeRate(
        @Query("base") base: String,
        @Body currenciesList: List<String>,
    )

    @GET("")

}