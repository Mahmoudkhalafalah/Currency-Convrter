package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.data_source.model.currency_enriched_data.CurrencyEnrichedDataModel
import com.example.currencyconverter.data.data_source.model.currency_exchange.CurrencyExchangeModel
import com.example.currencyconverter.data.data_source.model.currency_exchange_with_amount.CurrencyAmountExchangeModel
import com.example.currencyconverter.data.data_source.model.currency_historical_data.CurrencyHistoricalDataModel
import com.example.currencyconverter.data.data_source.network.NetworkServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://v6.exchangerate-api.com/v6/ecf10bab01b34bf0de9636e1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitAPI: NetworkServices = retrofit.create(NetworkServices::class.java)


    suspend fun getCurrencyRate(
        base: String,
        target: String,
    ): CurrencyExchangeModel {
        return retrofitAPI.getCurrencyRate(base, target)
    }

    suspend fun getCurrencyRateWithAmount(
        base: String,
        target: String,
        amount: Double,
    ): CurrencyAmountExchangeModel {
        return retrofitAPI.getCurrencyRateWithAmount(base, target, amount)
    }

    suspend fun getCurrencyEnrichedData(
        base: String,
        target: String,
    ): CurrencyEnrichedDataModel {
        return retrofitAPI.getCurrencyEnrichedData(base, target)
    }

    suspend fun getCurrencyHistoricalData(
        base: String,
        year: Int,
        month: Int,
        day: Int,
    ):CurrencyHistoricalDataModel {
        return retrofitAPI.getCurrencyHistoricalData(base, year, month, day)
    }
}