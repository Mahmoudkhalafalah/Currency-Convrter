package com.example.currencyconverter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.currencyconverter.presentation.edit_show_favourites.FavouritesViewModel
import com.example.currencyconverter.presentation.ui.theme.CurrencyConverterTheme
import com.example.currencyconverter.presentation.upperUi.ConvertAndCompareViewModel
import com.example.currencyconverter.presentation.upperUi.Main

class MainActivity : ComponentActivity() {
    private val favouritesViewModel by viewModels<FavouritesViewModel>()
    private val compareAndConvertViewModel by viewModels<ConvertAndCompareViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {

                        Main(
                            compareButtonClicked = compareAndConvertViewModel.compareButtonClicked.value,
                            convertButtonClicked = compareAndConvertViewModel.convertButtonClicked.value,
                            onCompareButtonClick = { compareAndConvertViewModel.onCompareToggleButtonClick() },
                            onConvertButtonClick = { compareAndConvertViewModel.onConvertToggleButtonClick() },
                            currenciesList = compareAndConvertViewModel.currenciesList.value,
                            isFromExpanded = compareAndConvertViewModel.isFromMenuExpanded.value,
                            isToExpanded = compareAndConvertViewModel.isToMenuExpanded.value,
                            fromSelectedCurrencyCode = compareAndConvertViewModel.fromSelectedCurrencyCode.value,
                            fromSelectedCurrencyFlag = compareAndConvertViewModel.fromSelectedCurrencyFlag.value,
                            toSelectedCurrencyCode = compareAndConvertViewModel.toSelectedCurrencyCode.value,
                            toSelectedCurrencyFlag = compareAndConvertViewModel.toSelectedCurrencyFlag.value,
                            onDropDownMenuDismissRequest = {compareAndConvertViewModel.onDropDownMenuDismissRequest()},
                            onFromDropDownIconClick = {compareAndConvertViewModel.onFromDropDownMenuIconClick()},
                            onToDropDownIconClick = {compareAndConvertViewModel.onToDropDownMenuIconClick()},
                            onFromItemSelected = {code,flag->compareAndConvertViewModel.onFromCurrencySelect(code,flag)},
                            onToItemSelected = {code,flag->compareAndConvertViewModel.onToCurrencySelect(code,flag)},
                            convertButtonClick = {compareAndConvertViewModel.onConvertCurrencyButtonClick()},
                            amount = compareAndConvertViewModel.inputAmount.value,
                            convertedAmount = compareAndConvertViewModel.convertedAmount.value,
                            onConvertInputTextChange = {compareAndConvertViewModel.onInputTextChange(it)}
                        )
                        /*FavouritesList(
                            sheetVisibility = favouritesViewModel.dialogVisibility.value,
                            onIconClick = { favouritesViewModel.onAddFavouritesClick() },
                            favouriteCurrenciesList = favouritesViewModel.favouritesList.value,
                            currenciesList = favouritesViewModel.currenciesList.value,
                            onItemSelection = { id, state ->
                                favouritesViewModel.onItemSelect(
                                    state,
                                    id
                                )
                            },
                            onCloseIconClick = { favouritesViewModel.onCloseIconClick() },
                            onSheetDismissRequest = { favouritesViewModel.onSheetDismissRequest() }

                        )*/
                    }


                }
            }
        }
    }
}



