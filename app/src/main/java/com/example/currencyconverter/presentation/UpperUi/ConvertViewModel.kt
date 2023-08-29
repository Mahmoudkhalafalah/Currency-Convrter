package com.example.currencyconverter.presentation.upperui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.data_source.model.currencies.Data
import com.example.currencyconverter.domain.use_cases.ConvertCurrencyUseCase
import com.example.currencyconverter.domain.use_cases.GetAllCurrenciesUseCase
import com.example.currencyconverter.presentation.AppClass
import com.example.currencyconverter.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class ConvertViewModel(
    private val getCurrencies: GetAllCurrenciesUseCase = GetAllCurrenciesUseCase(),
    private val convertCurrency: ConvertCurrencyUseCase = ConvertCurrencyUseCase(),
) : BaseViewModel() {

    private val language = mutableStateOf("en")

    val context = AppClass.appContext
    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _convertButtonClicked = mutableStateOf(true)
    val convertButtonClicked = _convertButtonClicked

    private val _compareButtonClicked = mutableStateOf(false)
    val compareButtonClicked = _compareButtonClicked

    private val _currenciesList = mutableStateOf<List<Data>>(emptyList())
    val currenciesList = _currenciesList

    private val _fromSelectedCurrencyFlag = mutableStateOf("https://flagcdn.com/w80/eg.png")
    val fromSelectedCurrencyFlag = _fromSelectedCurrencyFlag

    private val _toSelectedCurrencyFlag = mutableStateOf("https://flagcdn.com/w80/eg.png")
    val toSelectedCurrencyFlag = _toSelectedCurrencyFlag

    private val _fromSelectedCurrencyCode = mutableStateOf("EGP")
    val fromSelectedCurrencyCode = _fromSelectedCurrencyCode

    private val _toSelectedCurrencyCode = mutableStateOf("EGP")
    val toSelectedCurrencyCode = _toSelectedCurrencyCode

    private val _isFromMenuExpanded = mutableStateOf(false)
    val isFromMenuExpanded = _isFromMenuExpanded

    private val _isToMenuExpanded = mutableStateOf(false)
    val isToMenuExpanded = _isToMenuExpanded

    private val _inputAmount = mutableStateOf("")
    val inputAmount = _inputAmount

    private val _convertedAmount = mutableStateOf("")
    val convertedAmount = _convertedAmount

    init {
        viewModelScope.launch {
            _currenciesList.value = getCurrencies.getAllCurrencies().data
        }
    }

    fun onDropDownMenuDismissRequest() {
        isFromMenuExpanded.value = false
        isToMenuExpanded.value = false
    }

    fun onFromDropDownMenuIconClick() {
        isFromMenuExpanded.value = isFromMenuExpanded.value.not()
    }

    fun onToDropDownMenuIconClick() {
        isToMenuExpanded.value = isToMenuExpanded.value.not()
    }

    fun onFromCurrencySelect(code: String, flag: String) {
        fromSelectedCurrencyCode.value = code
        fromSelectedCurrencyFlag.value = flag
        isFromMenuExpanded.value = isFromMenuExpanded.value.not()
    }

    fun onToCurrencySelect(code: String, flag: String) {
        toSelectedCurrencyCode.value = code
        toSelectedCurrencyFlag.value = flag
        isToMenuExpanded.value = isToMenuExpanded.value.not()
    }

    fun onCompareToggleButtonClick() {
        _convertButtonClicked.value = false
        _compareButtonClicked.value = true
    }

    fun onConvertToggleButtonClick() {
        _compareButtonClicked.value = false
        _convertButtonClicked.value = true
    }

    fun onConvertCurrencyButtonClick() {
        if (_inputAmount.value.isNotEmpty()) {
            viewModelScope.launch {
                _convertedAmount.value =
                    (((convertCurrency.convertCurrency(
                        _fromSelectedCurrencyCode.value,
                        _toSelectedCurrencyCode.value,
                        _inputAmount.value.toDouble()
                    ).data.conversion_result * 100).roundToInt()) / 100f).toString()
            }
        } else {
            viewModelScope.launch {
                _error.emit("Enter valid Number")
            }
        }
    }

    fun onInputTextChange(text: String) {
        _inputAmount.value = text
    }

    fun onLanguageButtonClick() {
        when (LocaleListCompat.forLanguageTags(language.value)) {
            LocaleListCompat.forLanguageTags("en") -> {
                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags(
                        "ar"
                    )
                )
                language.value = "ar"
            }

            LocaleListCompat.forLanguageTags("ar") -> {
                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags(
                        "en"
                    )
                )
                language.value = "en"
            }
        }
        context.findActivity()?.runOnUiThread{
            AppCompatDelegate.getApplicationLocales()
        }
    }

    private fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null

    }
}

