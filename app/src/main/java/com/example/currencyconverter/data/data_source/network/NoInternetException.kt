package com.example.currencyconverter.data.data_source.network

import okio.IOException

class NoInternetException(message: String) : IOException() {
}