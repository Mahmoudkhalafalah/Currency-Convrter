package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.currencies.SupportedCurrenciesResponseModel
import com.example.currencyconverter.data.repository.Repository
import com.example.currencyconverter.domain.model.Currency

class GetAllCurrenciesUseCase(private val repository: Repository = Repository()) {
    suspend fun getAllCurrencies(): SupportedCurrenciesResponseModel {
        return repository.getAllSupportedCurrencies()
    }

    suspend fun getAllCurrenciesWithSelection(): List<Currency> {
        return repository.getAllSupportedCurrencies().data.map {
            Currency(
                flag = it.flagUrl,
                currencyRate = 0.0,
                currencyCode = it.code,
                name = it.name
            )
        }
    }
}