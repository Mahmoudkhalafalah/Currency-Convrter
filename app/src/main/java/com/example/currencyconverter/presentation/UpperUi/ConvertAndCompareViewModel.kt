package com.example.currencyconverter.presentation.upperUi

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ConvertAndCompareViewModel : ViewModel() {

    private val _convertButtonClicked = mutableStateOf(true)
    val convertButtonClicked = _convertButtonClicked

    private val _compareButtonClicked = mutableStateOf(false)
    val compareButtonClicked = _compareButtonClicked

    fun onCompareButtonClick() {
        _convertButtonClicked.value = false
        _compareButtonClicked.value = true
    }

    fun onConvertButtonClick() {
        _compareButtonClicked.value = false
        _convertButtonClicked.value = true
    }

}