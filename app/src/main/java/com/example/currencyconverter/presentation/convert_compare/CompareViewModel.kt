package com.example.currencyconverter.presentation.convert_compare

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.use_cases.CompareCurrenciesUseCase
import com.example.currencyconverter.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class CompareViewModel(
    private val compareCurrencies: CompareCurrenciesUseCase = CompareCurrenciesUseCase(),
) :
    BaseViewModel() {

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _fromSelectedCurrencyFlag = mutableStateOf("https://flagsapi.com/EG/flat/64.png")
    val fromSelectedCurrencyFlag = _fromSelectedCurrencyFlag

    private val _firstTargetSelectedCurrencyFlag =
        mutableStateOf("https://flagsapi.com/EG/flat/64.png")
    val firstTargetSelectedCurrencyFlag = _firstTargetSelectedCurrencyFlag

    private val _secondTargetSelectedCurrencyFlag =
        mutableStateOf("https://flagsapi.com/EG/flat/64.png")
    val secondTargetSelectedCurrencyFlag = _secondTargetSelectedCurrencyFlag

    private val _fromSelectedCurrencyCode = mutableStateOf("EGP")
    val fromSelectedCurrencyCode = _fromSelectedCurrencyCode

    private val _firstTargetSelectedCurrencyCode = mutableStateOf("EGP")
    val firstTargetSelectedCurrencyCode = _firstTargetSelectedCurrencyCode

    private val _secondTargetSelectedCurrencyCode = mutableStateOf("EGP")
    val secondTargetSelectedCurrencyCode = _secondTargetSelectedCurrencyCode

    private val _isFromMenuExpanded = mutableStateOf(false)
    val isFromMenuExpanded = _isFromMenuExpanded

    private val _isFirstTargetMenuExpanded = mutableStateOf(false)
    val isFirstTargetMenuExpanded = _isFirstTargetMenuExpanded

    private val _isSecondTargetMenuExpanded = mutableStateOf(false)
    val isSecondTargetMenuExpanded = _isSecondTargetMenuExpanded

    private val _inputAmount = mutableStateOf("")
    val inputAmount = _inputAmount

    private val _firstTargetConvertedAmount = mutableStateOf("")
    val firstTargetConvertedAmount = _firstTargetConvertedAmount

    private val _secondTargetConvertedAmount = mutableStateOf("")
    val secondTargetConvertedAmount = _secondTargetConvertedAmount

    fun onFromDropDownMenuIconClick() {
        _isFromMenuExpanded.value = _isFromMenuExpanded.value.not()
    }

    fun onFirstTargetDropDownMenuIconClick() {
        _isFirstTargetMenuExpanded.value = _isFirstTargetMenuExpanded.value.not()
    }

    fun onSecondTargetDropDownMenuIconClick() {
        _isSecondTargetMenuExpanded.value = _isSecondTargetMenuExpanded.value.not()
    }

    fun onInputTextChange(text: String) {
        _inputAmount.value = text
    }

    fun onDropDownMenuDismissRequest() {
        _isFromMenuExpanded.value = false
        _isFirstTargetMenuExpanded.value = false
        _isSecondTargetMenuExpanded.value = false
    }

    fun onFromItemSelected(code: String, flag: String) {
        _fromSelectedCurrencyCode.value = code
        _fromSelectedCurrencyFlag.value = flag
        _isFromMenuExpanded.value = false
    }

    fun onFirstTargetItemSelected(code: String, flag: String) {
        _firstTargetSelectedCurrencyCode.value = code
        _firstTargetSelectedCurrencyFlag.value = flag
        _isFirstTargetMenuExpanded.value = false
    }

    fun onSecondTargetItemSelected(code: String, flag: String) {
        _secondTargetSelectedCurrencyCode.value = code
        _secondTargetSelectedCurrencyFlag.value = flag
        _isSecondTargetMenuExpanded.value = false
    }

    fun onCompareButtonClick() {
        if (_inputAmount.value.isNotEmpty()) {
            viewModelScope.launch(handler) {
                _loading.value = true
                val data = compareCurrencies.getCurrenciesComparison(
                    _fromSelectedCurrencyCode.value,
                    _firstTargetSelectedCurrencyCode.value,
                    _secondTargetSelectedCurrencyCode.value,
                    _inputAmount.value.toDouble()
                ).data
                _firstTargetConvertedAmount.value =
                    (((data.firstTargetCurrency.conversion_result * 100).roundToInt()) / 100f).toString()
                _secondTargetConvertedAmount.value =
                    (((data.secondTargetCurrency.conversion_result * 100).roundToInt()) / 100f).toString()
                _loading.value = false
            }
        } else {
            viewModelScope.launch {
                _error.emit("Enter valid Number")
            }
        }
    }
}