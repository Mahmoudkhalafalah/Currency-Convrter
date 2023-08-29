package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.comparison.CurrenciesComparisonResponseModel
import com.example.currencyconverter.data.repository.Repository

class CompareCurrenciesUseCase(private val repository: Repository = Repository()) {

    suspend fun getCurrenciesComparison(
        base: String,
        firstTarget: String,
        secondTarget: String,
        amount: Double,
    ): CurrenciesComparisonResponseModel {
        return repository.getExchangeAmountComparison(base, firstTarget, secondTarget, amount)
    }

}