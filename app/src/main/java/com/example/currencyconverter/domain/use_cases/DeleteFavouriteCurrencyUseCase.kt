package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.repository.Repository

class DeleteFavouriteCurrencyUseCase(private val repository: Repository = Repository()) {

    fun deleteCurrency(code:String) {
        repository.deleteCurrency(code)
    }
}