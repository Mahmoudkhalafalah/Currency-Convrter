package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.currency_historical_data.CurrencyHistoricalDataModel
import com.example.currencyconverter.data.repository.Repository

class GetCurrencyHistoricalDataUseCase(private val repository: Repository = Repository()) {

    suspend fun getCurrencyHistoricalDate(
        base: String,
        year: Int,
        month: Int,
        day: Int,
    ): CurrencyHistoricalDataModel {
        return repository.getCurrencyHistoricalData(base, year, month, day)
    }
}