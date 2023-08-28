package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.currencies.SupportedCurrenciesResponseModel
import com.example.currencyconverter.data.repository.Repository
import com.example.currencyconverter.domain.model.Currency

class GetAllCurrenciesUseCase(private val repository: Repository = Repository()) {
    suspend fun getAllCurrencies(): SupportedCurrenciesResponseModel {
        return repository.getAllSupportedCurrencies()
    }

}