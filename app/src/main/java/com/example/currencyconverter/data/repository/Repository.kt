package com.example.currencyconverter.data.repository

import androidx.room.Room
import com.example.currencyconverter.data.data_source.database.CurrenciesDataBase
import com.example.currencyconverter.data.data_source.model.comparison.CurrenciesComparisonResponseModel
import com.example.currencyconverter.data.data_source.model.currencies.SupportedCurrenciesResponseModel
import com.example.currencyconverter.data.data_source.model.pair_conversion.PairConversionResponseModel
import com.example.currencyconverter.data.data_source.network.NetworkServices
import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.presentation.AppClass
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private val context = AppClass.appContext

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://currencyconversionproject-production.up.railway.app/")
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

    fun getAllCurrencies(): List<Currency> {
        return currencyDao.getAllCurrencies()
    }

    fun getSelectedCurrencies(): List<Currency> {
        return currencyDao.getSelectedCurrencies()
    }

    fun addCurrency(currency: Currency) {
        currencyDao.insertCurrency(currency)
    }

    fun updateCurrencySelectionState(state: Boolean, id: Int) {
        currencyDao.updateCurrencySelectionState(state, id)
    }
}