package com.example.currencyconverter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CurrenciesList")
data class Currency(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val code: String,
    val flag: String,
    val name: String,
)