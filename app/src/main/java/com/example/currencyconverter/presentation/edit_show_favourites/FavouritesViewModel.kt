package com.example.currencyconverter.presentation.edit_show_favourites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.domain.use_cases.AddFavouriteCurrencyUseCase
import com.example.currencyconverter.domain.use_cases.DeleteFavouriteCurrencyUseCase
import com.example.currencyconverter.domain.use_cases.FindFavouriteCurrencyUseCase
import com.example.currencyconverter.domain.use_cases.GetFavouriteCurrenciesCodesUseCase
import com.example.currencyconverter.domain.use_cases.GetFavouriteCurrenciesRatesUseCase
import com.example.currencyconverter.domain.use_cases.GetFavouriteCurrenciesUseCase
import com.example.currencyconverter.presentation.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val findFavouriteCurrency: FindFavouriteCurrencyUseCase = FindFavouriteCurrencyUseCase(),
    private val addFavouriteCurrency: AddFavouriteCurrencyUseCase = AddFavouriteCurrencyUseCase(),
    private val getFavouriteCurrenciesCodes: GetFavouriteCurrenciesCodesUseCase = GetFavouriteCurrenciesCodesUseCase(),
    private val getFavouriteCurrenciesRates: GetFavouriteCurrenciesRatesUseCase = GetFavouriteCurrenciesRatesUseCase(),
    private val getFavouriteCurrencies: GetFavouriteCurrenciesUseCase = GetFavouriteCurrenciesUseCase(),
    private val deleteCurrency: DeleteFavouriteCurrencyUseCase = DeleteFavouriteCurrencyUseCase(),
) : BaseViewModel() {

    private val _favouritesListRates =
        mutableStateOf<List<Double>>(emptyList())
    val favouritesListRates = _favouritesListRates

    private val _isAppLoaded = MutableSharedFlow<Boolean>()
    val isAppLoaded = _isAppLoaded.asSharedFlow()

    private val _favouritesList =
        mutableStateOf<List<Currency>>(emptyList())
    val favouritesList = _favouritesList

    private val _currenciesList = mutableStateOf<List<Currency>>(emptyList())
    val currenciesList = _currenciesList

    private val _dialogVisibility = mutableStateOf(false)
    val dialogVisibility = _dialogVisibility

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            updateFavouritesList()
            delay(2000)
            _isAppLoaded.emit(true)
        }
    }

    fun updateFavouritesList(code: String = "EGP") {
        viewModelScope.launch(handler) {
            _isLoading.value = true
            val list = getFavouriteCurrenciesCodes.getSelectedCurrencies()
            _favouritesListRates.value =
                (getFavouriteCurrenciesRates.getFavouriteCurrenciesRates(code, list).data)
            _favouritesList.value = getFavouriteCurrencies.getAllCurrencies()
            _isLoading.value = false
        }
    }

    fun onAddFavouritesClick() {
        _dialogVisibility.value = true
    }

    fun onSheetDismissRequest() {
        _dialogVisibility.value = false
        updateFavouritesList()
    }

    fun onCloseIconClick() {
        _dialogVisibility.value = false
        updateFavouritesList()
    }

    fun onItemSelect(code: String, name: String, flag: String) {
        if (isItemSelected(code)) {
            deleteCurrency.deleteCurrency(code)
        } else {
            addFavouriteCurrency.addCurrency(Currency(code = code, name = name, flag = flag))
        }
        updateFavouritesList()
    }

    fun isItemSelected(code: String): Boolean {
        return findFavouriteCurrency.findCurrency(code)
    }
}