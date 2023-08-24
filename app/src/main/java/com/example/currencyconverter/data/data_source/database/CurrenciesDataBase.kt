package com.example.currencyconverter.data.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencyconverter.domain.model.Currency

@Database(
    entities = [Currency::class],
    version = 1,
    exportSchema = false
)
abstract class CurrenciesDataBase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
}