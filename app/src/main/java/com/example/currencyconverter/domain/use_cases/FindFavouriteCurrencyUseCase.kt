package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.repository.Repository

class FindFavouriteCurrencyUseCase(private val repository: Repository= Repository()) {

    fun findCurrency(code:String):Boolean{
        return repository.findCurrency(code)
    }
}