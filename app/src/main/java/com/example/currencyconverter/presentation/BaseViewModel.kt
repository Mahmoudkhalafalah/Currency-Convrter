package com.example.currencyconverter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {

    private val _throwError = MutableSharedFlow<String>()
    val trowError = _throwError.asSharedFlow()

    val handler = CoroutineExceptionHandler { _, exception ->
        if (exception is HttpException) {
            when (exception.code()) {
                400 -> {
                    viewModelScope.launch {
                        _throwError.emit("bad response")
                    }
                }

                500 -> {
                    viewModelScope.launch {
                        _throwError.emit("internal server error")
                    }
                }

                401 -> {
                    viewModelScope.launch {
                        _throwError.emit("un authorized")
                    }
                }

                403 -> {
                    viewModelScope.launch {
                        _throwError.emit("Error")
                    }
                }

                else -> {
                    viewModelScope.launch {
                        _throwError.emit("Error")
                    }
                }
            }
        }
    }
}