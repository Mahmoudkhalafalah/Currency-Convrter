package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.pair_conversion.PairConversionResponseModel
import com.example.currencyconverter.data.repository.Repository

class ConvertCurrencyUseCase(private val repository: Repository = Repository()) {

    suspend fun convertCurrency(
        base: String,
        target: String,
        amount: Double,
    ): PairConversionResponseModel {
        return repository.getExchangeAmount(base, target, amount)
    }
}