package com.example.currencyconverter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CurrenciesList")
data class Currency(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val flag: String,
    val currencyCode: String,
    val currencyRate: Double,
    val name:String,
    val isSelected: Boolean = false,
)