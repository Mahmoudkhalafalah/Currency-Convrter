package com.example.currencyconverter.data.data_source.model.currency_enriched_data

data class TargetData(
    val currency_name: String,
    val currency_name_short: String,
    val display_symbol: String,
    val flag_url: String,
    val locale: String,
    val two_letter_code: String,
)