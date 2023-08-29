package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.currencies.SupportedCurrenciesResponseModel
import com.example.currencyconverter.data.repository.Repository

class GetAllCurrenciesUseCase(private val repository: Repository = Repository()) {

    suspend fun getAllCurrencies(): SupportedCurrenciesResponseModel {
        return repository.getAllSupportedCurrencies()
    }

}