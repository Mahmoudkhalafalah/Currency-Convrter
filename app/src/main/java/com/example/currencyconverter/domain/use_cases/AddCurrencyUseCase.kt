package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.repository.Repository
import com.example.currencyconverter.domain.model.Currency

class AddCurrencyUseCase(private val repository: Repository = Repository()) {
    fun addCurrency(currency: Currency) {
        repository.addCurrency(currency)
    }
}