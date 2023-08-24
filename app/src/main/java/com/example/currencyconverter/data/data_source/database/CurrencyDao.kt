package com.example.currencyconverter.data.data_source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.currencyconverter.domain.model.Currency

@Dao
interface CurrencyDao {

    @Insert
    fun insertCurrency(currency: Currency)

    @Query("UPDATE CurrenciesList SET isSelected = :isSelected WHERE id = :id")
    fun updateCurrencySelectionState(isSelected: Boolean, id: Int)

    @Query("SELECT * FROM CurrenciesList")
    fun getAllCurrencies(): List<Currency>

    @Query("SELECT * FROM CurrenciesList WHERE isSelected = 1")
    fun getSelectedCurrencies(): List<Currency>

}