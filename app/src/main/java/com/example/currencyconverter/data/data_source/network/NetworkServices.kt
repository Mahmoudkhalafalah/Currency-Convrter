package com.example.currencyconverter.data.data_source.network

import com.example.currencyconverter.data.data_source.model.currency_enriched_data.CurrencyEnrichedDataModel
import com.example.currencyconverter.data.data_source.model.currency_exchange.CurrencyExchangeModel
import com.example.currencyconverter.data.data_source.model.currency_exchange_with_amount.CurrencyAmountExchangeModel
import com.example.currencyconverter.data.data_source.model.currency_historical_data.CurrencyHistoricalDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkServices {

    @GET("pair/{base}/{target}")
    suspend fun getCurrencyRate(
        @Path("base") baseCurrency: String,
        @Path("target") targetCurrency: String,
    ): CurrencyExchangeModel

    @GET("pair/{base}/{target}/{amount}")
    suspend fun getCurrencyRateWithAmount(
        @Path("base") baseCurrency: String,
        @Path("target") targetCurrency: String,
        @Path("amount") amount: Double,
    ): CurrencyAmountExchangeModel

    @GET("enriched/{base}/{target}")
    suspend fun getCurrencyEnrichedData(
        @Path("base") baseCurrency: String,
        @Path("target") targetCurrency: String,
    ): CurrencyEnrichedDataModel

    @GET("history/{base}/{YEAR}/{MONTH}/{DAY}")
    suspend fun getCurrencyHistoricalData(
        @Path("base") baseCurrency: String,
        @Path("YEAR") year: Int,
        @Path("MONTH") month: Int,
        @Path("DAY") day: Int,
    ): CurrencyHistoricalDataModel


}