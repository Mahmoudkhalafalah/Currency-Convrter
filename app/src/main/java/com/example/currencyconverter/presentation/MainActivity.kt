package com.example.currencyconverter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.currencyconverter.presentation.edit_show_favourites.FavouritesList
import com.example.currencyconverter.presentation.edit_show_favourites.FavouritesViewModel
import com.example.currencyconverter.presentation.ui.theme.CurrencyConverterTheme

class MainActivity : ComponentActivity() {
    private val favouritesViewModel by viewModels<FavouritesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FavouritesList(
                        dialogVisibility = favouritesViewModel.dialogVisibility.value,
                        onIconClick = { favouritesViewModel.onAddFavouritesClick() },
                        favouriteCurrenciesList = favouritesViewModel.favouritesList.value,
                        currenciesList = favouritesViewModel.currenciesList.value,
                        onItemSelection = { id, state ->
                            favouritesViewModel.onItemSelect(
                                state,
                                id
                            )
                        },
                        onDialogCloseClick = { favouritesViewModel.onDialogCloseClick() },
                        onDismissRequest = { favouritesViewModel.onDialogDismissRequest() }
                    )
                }
            }
        }
    }
}
