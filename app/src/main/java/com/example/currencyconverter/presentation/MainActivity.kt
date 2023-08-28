package com.example.currencyconverter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.presentation.edit_show_favourites.FavouritesList
import com.example.currencyconverter.presentation.edit_show_favourites.FavouritesViewModel
import com.example.currencyconverter.presentation.ui.theme.CurrencyConverterTheme
import com.example.currencyconverter.presentation.upperUi.CompareViewModel
import com.example.currencyconverter.presentation.upperUi.ConvertViewModel
import com.example.currencyconverter.presentation.upperUi.Header
import com.example.currencyconverter.presentation.upperUi.Main

class MainActivity : ComponentActivity() {
    private val favouritesViewModel by viewModels<FavouritesViewModel>()
    private val convertViewModel by viewModels<ConvertViewModel>()
    private val compareViewModel by viewModels<CompareViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                LaunchedEffect(key1 = true) {

                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxHeight()) {
                        Header(
                            onConvertToggleButtonClick = { convertViewModel.onConvertToggleButtonClick() },
                            onCompareToggleButtonClick = { convertViewModel.onCompareToggleButtonClick() },
                            convertButtonClicked = convertViewModel.convertButtonClicked.value,
                            compareButtonClicked = convertViewModel.compareButtonClicked.value
                        )
                        LazyColumn {
                            item {
                                Main(
                                    convertToggleButtonClicked = convertViewModel.convertButtonClicked.value,
                                    compareToggleButtonClicked = convertViewModel.compareButtonClicked.value,
                                    currenciesList = convertViewModel.currenciesList.value,
                                    isConvertFromMenuExpanded = convertViewModel.isFromMenuExpanded.value,
                                    isConvertToMenuExpanded = convertViewModel.isToMenuExpanded.value,
                                    convertFromSelectedCurrencyCode = convertViewModel.fromSelectedCurrencyCode.value,
                                    convertFromSelectedCurrencyFlag = convertViewModel.fromSelectedCurrencyFlag.value,
                                    convertToSelectedCurrencyCode = convertViewModel.toSelectedCurrencyCode.value,
                                    convertToSelectedCurrencyFlag = convertViewModel.toSelectedCurrencyFlag.value,
                                    onConvertDropDownMenusDismissRequest = { convertViewModel.onDropDownMenuDismissRequest() },
                                    onConvertFromDropDownIconClick = { convertViewModel.onFromDropDownMenuIconClick() },
                                    onConvertToDropDownIconClick = { convertViewModel.onToDropDownMenuIconClick() },
                                    onConvertFromItemSelected = { code, flag ->
                                        convertViewModel.onFromCurrencySelect(
                                            code,
                                            flag
                                        )
                                    },
                                    onConvertToItemSelected = { code, flag ->
                                        convertViewModel.onToCurrencySelect(
                                            code,
                                            flag
                                        )
                                    },
                                    convertButtonClick = { convertViewModel.onConvertCurrencyButtonClick() },
                                    inputConvertAmount = convertViewModel.inputAmount.value,
                                    convertedAmount = convertViewModel.convertedAmount.value,
                                    onConvertInputTextChange = {
                                        convertViewModel.onInputTextChange(
                                            it
                                        )
                                    },
                                    compareInputAmount = compareViewModel.inputAmount.value,
                                    firstTarget = compareViewModel.firstTargetConvertedAmount.value,
                                    secondTarget = compareViewModel.secondTargetConvertedAmount.value,
                                    onCompareInputTextChange = {
                                        compareViewModel.onInputTextChange(
                                            it
                                        )
                                    },
                                    isCompareFromMenuExpanded = compareViewModel.isFromMenuExpanded.value,
                                    isFirstTargetMenuCompareExpanded = compareViewModel.isFirstTargetMenuExpanded.value,
                                    isSecondTargetMenuCompareExpanded = compareViewModel.isSecondTargetMenuExpanded.value,
                                    fromSelectedCompareFlag = compareViewModel.fromSelectedCurrencyFlag.value,
                                    firstTargetCompareFlag = compareViewModel.firstTargetSelectedCurrencyFlag.value,
                                    secondTargetCompareFlag = compareViewModel.secondTargetSelectedCurrencyFlag.value,
                                    fromSelectedCompareCode = compareViewModel.fromSelectedCurrencyCode.value,
                                    firstTargetCompareCode = compareViewModel.firstTargetSelectedCurrencyCode.value,
                                    secondTargetCompareCode = compareViewModel.secondTargetSelectedCurrencyCode.value,
                                    onFromCompareItemSelected = { code, flag ->
                                        compareViewModel.onFromItemSelected(
                                            code,
                                            flag
                                        )
                                    },
                                    onFirstTargetCompareItemSelected = { code, flag ->
                                        compareViewModel.onFirstTargetItemSelected(
                                            code,
                                            flag
                                        )
                                    },
                                    onSecondTargetCompareItemSelected = { code, flag ->
                                        compareViewModel.onSecondTargetItemSelected(
                                            code,
                                            flag
                                        )
                                    },
                                    onFromDropDownCompareIconClick = { compareViewModel.onFromDropDownMenuIconClick() },
                                    onFirstTargetDropDownCompareIconClick = { compareViewModel.onFirstTargetDropDownMenuIconClick() },
                                    onSecondTargetDropDownCompareIconClick = { compareViewModel.onSecondTargetDropDownMenuIconClick() },
                                    onCompareDropDownMenusDismissRequest = { compareViewModel.onDropDownMenuDismissRequest() },
                                    onCompareButtonClick = { compareViewModel.onCompareButtonClick() }
                                )
                            }
                            item {
                                FavouritesList(
                                    sheetVisibility = favouritesViewModel.dialogVisibility.value,
                                    onIconClick = { favouritesViewModel.onAddFavouritesClick() },
                                    favouriteCurrenciesList = favouritesViewModel.favouritesList.value,
                                    currenciesList = convertViewModel.currenciesList.value,
                                    onItemSelection = { code, name, flag ->
                                        favouritesViewModel.onItemSelect(code, name, flag)
                                    },
                                    onCloseIconClick = { favouritesViewModel.onCloseIconClick() },
                                    onSheetDismissRequest = { favouritesViewModel.onSheetDismissRequest() },
                                    isItemSelected = { favouritesViewModel.isItemSelected(it) },
                                    favouriteListRates = favouritesViewModel.favouritesListRates.value
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}




