package com.example.currencyconverter.presentation.edit_show_favourites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.domain.use_cases.AddCurrencyUseCase
import com.example.currencyconverter.domain.use_cases.GetAllCurrenciesUseCase
import com.example.currencyconverter.domain.use_cases.GetSelectedCurrenciesUseCase
import com.example.currencyconverter.domain.use_cases.UpdateCurrencySelectionStateUseCase

class FavouritesViewModel(
    private val getAllCurrencies: GetAllCurrenciesUseCase = GetAllCurrenciesUseCase(),
    private val getSelectedCurrencies: GetSelectedCurrenciesUseCase = GetSelectedCurrenciesUseCase(),
    private val addCurrency: AddCurrencyUseCase = AddCurrencyUseCase(),
    private val updateCurrency: UpdateCurrencySelectionStateUseCase = UpdateCurrencySelectionStateUseCase(),
) : ViewModel() {


    private val _favouritesList =
        mutableStateOf<List<Currency>>(emptyList())
    val favouritesList = _favouritesList

    private val _currenciesList = mutableStateOf<List<Currency>>(emptyList())
    val currenciesList = _currenciesList

    private val _dialogVisibility = mutableStateOf(false)
    val dialogVisibility = _dialogVisibility

    init {
        repeat(10) {
            addCurrency.addCurrency(
                Currency(
                    flag = "https://flagcdn.com/32x24/eu.png",
                    currencyCode = "EGP",
                    currencyRate = 1.5,
                    name = " "
                )
            )
        }
        updateFavouritesList()
    }

    private fun updateFavouritesList() {
        _favouritesList.value = getSelectedCurrencies.getSelectedCurrencies()
    }


    /*private fun updateCurrenciesList() {
        _currenciesList.value = getAllCurrencies.getAllCurrencies()
    }*/

    fun onAddFavouritesClick() {
        //updateCurrenciesList()
        _dialogVisibility.value = true
    }

    fun onSheetDismissRequest() {
        updateFavouritesList()
        _dialogVisibility.value = false
    }

    fun onCloseIconClick() {
        updateFavouritesList()
        _dialogVisibility.value = false
    }

    fun onItemSelect(state: Boolean, id: Int) {
        updateCurrency.updateCurrencySelectionState(state, id)
        //updateCurrenciesList()
    }


}