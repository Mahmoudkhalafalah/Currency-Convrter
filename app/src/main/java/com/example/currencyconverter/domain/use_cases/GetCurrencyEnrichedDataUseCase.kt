package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.currency_enriched_data.CurrencyEnrichedDataModel
import com.example.currencyconverter.data.repository.Repository

class GetCurrencyEnrichedDataUseCase(private val repository: Repository = Repository()) {

    suspend fun getCurrencyEnrichedData(base: String, target: String): CurrencyEnrichedDataModel {
        return repository.getCurrencyEnrichedData(base, target)
    }
}