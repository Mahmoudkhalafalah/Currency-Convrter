package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.currency_exchange.CurrencyExchangeModel
import com.example.currencyconverter.data.repository.Repository

class GetCurrencyRateUseCase(
    private val repository: Repository = Repository(),
) {
    suspend fun getCurrencyRate(base: String, target: String): CurrencyExchangeModel {
        return repository.getCurrencyRate(base, target)
    }


}