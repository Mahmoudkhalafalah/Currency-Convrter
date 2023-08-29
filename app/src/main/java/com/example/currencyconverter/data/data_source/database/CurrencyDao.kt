package com.example.currencyconverter.data.data_source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.currencyconverter.domain.model.Currency

@Dao
interface CurrencyDao {

    @Insert
    fun insertCurrency(currency: Currency)

    @Query("DELETE FROM CurrenciesList WHERE code = :code ")
    fun deleteCurrency(code: String)

    @Query("SELECT code FROM CurrenciesList")
    fun getAllCurrenciesCodes(): List<String>

    @Query("SELECT * FROM CurrenciesList WHERE code= :code")
    fun findCurrency(code: String): Boolean

    @Query("SELECT*FROM CurrenciesList")
    fun getAllCurrencies(): List<Currency>


}