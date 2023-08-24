package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.repository.Repository
import com.example.currencyconverter.domain.model.Currency

class GetSelectedCurrenciesUseCase(private val repository: Repository= Repository()) {

    fun getSelectedCurrencies():List<Currency>{
        return repository.getSelectedCurrencies()
    }
}