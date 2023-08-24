package com.example.currencyconverter.domain.use_cases

import com.example.currencyconverter.data.repository.Repository

class UpdateCurrencySelectionStateUseCase(private val repository: Repository = Repository()) {

    fun updateCurrencySelectionState(state: Boolean, id: Int) {
        repository.updateCurrencySelectionState(state, id)
    }
}