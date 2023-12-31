package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.repository.Repository
import com.example.currencyconverter.domain.model.Currency

class GetFavouriteCurrenciesUseCase(private val repository: Repository = Repository()) {

    fun getAllCurrencies(): List<Currency> {
        return repository.getAllCurrencies()
    }

}