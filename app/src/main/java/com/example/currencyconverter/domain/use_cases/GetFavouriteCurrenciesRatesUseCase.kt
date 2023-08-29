package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.data_source.model.favourites.FavouriteCurrenciesRatesResponseModel
import com.example.currencyconverter.data.repository.Repository

class GetFavouriteCurrenciesRatesUseCase(private val repository: Repository = Repository()) {

    suspend fun getFavouriteCurrenciesRates(
        base: String,
        favouritesList: List<String>,
    ): FavouriteCurrenciesRatesResponseModel {
        return repository.getFavouriteCurrenciesRates(base, favouritesList)
    }

}