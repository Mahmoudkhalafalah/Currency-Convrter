package com.example.currencyconverter.presentation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {

    private val language = mutableStateOf("en")

    private val context = AppClass.appContext

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
        context.findActivity()?.runOnUiThread {
            AppCompatDelegate.getApplicationLocales()
        }
    }

    private fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null

    }
}