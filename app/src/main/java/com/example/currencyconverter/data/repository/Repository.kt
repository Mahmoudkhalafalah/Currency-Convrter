package com.example.currencyconverter.data.repository

import androidx.room.Room
import com.example.currencyconverter.data.data_source.database.CurrenciesDataBase
import com.example.currencyconverter.data.data_source.model.comparison.CurrenciesComparisonResponseModel
import com.example.currencyconverter.data.data_source.model.currencies.SupportedCurrenciesResponseModel
import com.example.currencyconverter.data.data_source.model.favourites.FavouriteCurrenciesRatesResponseModel
import com.example.currencyconverter.data.data_source.model.pair_conversion.PairConversionResponseModel
import com.example.currencyconverter.data.data_source.network.NetworkConnectionInterceptor
import com.example.currencyconverter.data.data_source.network.NetworkServices
import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.presentation.AppClass
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private val context = AppClass.appContext

    val client = OkHttpClient.Builder().addInterceptor(NetworkConnectionInterceptor()).build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://tiresome-part-production.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitAPI: NetworkServices = retrofit.create(NetworkServices::class.java)

    private val dataBase =
        Room.databaseBuilder(context, CurrenciesDataBase::class.java, "CurrenciesDataBase")
            .allowMainThreadQueries().build()

    private val currencyDao = dataBase.currencyDao()

    suspend fun getExchangeAmount(
        base: String,
        target: String,
        amount: Double,
    ): PairConversionResponseModel {
        return retrofitAPI.getExchangeAmount(base, target, amount)
    }

    suspend fun getExchangeAmountComparison(
        base: String,
        firstTarget: String,
        secondTarget: String,
        amount: Double,
    ): CurrenciesComparisonResponseModel {
        return retrofitAPI.getExchangeAmountComparison(base, firstTarget, secondTarget, amount)
    }

    suspend fun getAllSupportedCurrencies(): SupportedCurrenciesResponseModel {
        return retrofitAPI.getAllSupportedCurrencies()
    }

    suspend fun getFavouriteCurrenciesRates(
        base: String,
        favouritesList: List<String>,
    ): FavouriteCurrenciesRatesResponseModel {
        return retrofitAPI.getFavouriteCurrenciesExchangeRate(base, favouritesList)
    }

    fun getFavouriteCurrenciesCodes(): List<String> {
        return currencyDao.getAllCurrenciesCodes()
    }

    fun getAllCurrencies(): List<Currency> {
        return currencyDao.getAllCurrencies()
    }

    fun addCurrency(currency: Currency) {
        currencyDao.insertCurrency(currency)
    }

    fun deleteCurrency(code: String) {
        currencyDao.deleteCurrency(code)
    }

    fun findCurrency(code: String): Boolean {
        return currencyDao.findCurrency(code)
    }
}