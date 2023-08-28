package com.example.currencyconverter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.currencyconverter.presentation.edit_show_favourites.FavouritesList
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
                            onCompareButtonClick = { compareAndConvertViewModel.onCompareButtonClick() },
                            onConvertButtonClick = { compareAndConvertViewModel.onConvertButtonClick() }
                        )
                        FavouritesList(
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

                        )
                    }


                }
            }
        }
    }
}



