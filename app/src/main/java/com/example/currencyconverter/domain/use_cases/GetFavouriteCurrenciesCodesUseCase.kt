package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.repository.Repository

class GetFavouriteCurrenciesCodesUseCase(private val repository: Repository = Repository()) {

    fun getSelectedCurrencies(): List<String> {
        return repository.getFavouriteCurrenciesCodes()
    }

}