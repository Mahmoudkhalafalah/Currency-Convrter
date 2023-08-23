package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.currency_exchange_with_amount.CurrencyAmountExchangeModel
import com.example.currencyconverter.data.repository.Repository

class GetCurrencyRateWithAmountUseCase(private val repository: Repository = Repository()) {

    suspend fun getCurrencyRateWithAmount(
        base: String,
        target: String,
        amount: Double,
    ): CurrencyAmountExchangeModel {
        return repository.getCurrencyRateWithAmount(base, target, amount)
    }
}