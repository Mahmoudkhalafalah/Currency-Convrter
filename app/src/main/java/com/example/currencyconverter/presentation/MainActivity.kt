package com.example.currencyconverter.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.convert_compare.CompareViewModel
import com.example.currencyconverter.presentation.convert_compare.ConvertViewModel
import com.example.currencyconverter.presentation.convert_compare.Header
import com.example.currencyconverter.presentation.convert_compare.Main
import com.example.currencyconverter.presentation.edit_show_favourites.FavouritesList
import com.example.currencyconverter.presentation.edit_show_favourites.FavouritesViewModel
import com.example.currencyconverter.presentation.ui.theme.CurrencyConverterTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class MainActivity : AppCompatActivity() {
    private val favouritesViewModel by viewModels<FavouritesViewModel>()
    private val convertViewModel by viewModels<ConvertViewModel>()
    private val compareViewModel by viewModels<CompareViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                LaunchedEffect(key1 = true) {
                    favouritesViewModel.trowError.collect {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                LaunchedEffect(key1 = true) {
                    convertViewModel.error.collect {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                LaunchedEffect(key1 = true) {
                    compareViewModel.error.collect {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG)
                            .show()
                    }
                }
                LaunchedEffect(key1 = true) {
                    favouritesViewModel.isAppLoaded.collect {
                        if (it) {
                            navController.navigate("mainScreen")
                            Toast.makeText(this@MainActivity, "Welcome Back", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val isLoading = favouritesViewModel.isLoading.collectAsState().value
                    val state = rememberSwipeRefreshState(isRefreshing = isLoading)
                    val composition by rememberLottieComposition(
                        LottieCompositionSpec.RawRes(R.raw.loading)
                    )
                    NavHost(navController = navController, startDestination = "Splash") {
                        composable("Splash") {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val progress by animateLottieCompositionAsState(
                                    composition,
                                    iterations = LottieConstants.IterateForever,
                                    isPlaying = true,
                                    speed = 1f,
                                    restartOnPlay = true
                                )
                                LottieAnimation(
                                    composition,
                                    progress,
                                    modifier = Modifier.size(200.dp)
                                )
                            }

                        }
                        composable("mainScreen") {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .background(Color.White)
                            ) {
                                Header(
                                    onConvertToggleButtonClick = { convertViewModel.onConvertToggleButtonClick() },
                                    onCompareToggleButtonClick = { convertViewModel.onCompareToggleButtonClick() },
                                    convertButtonClicked = convertViewModel.convertButtonClicked.value,
                                    compareButtonClicked = convertViewModel.compareButtonClicked.value,
                                    onLanguageButtonClick = { convertViewModel.onLanguageButtonClick() }
                                )
                                SwipeRefresh(
                                    state = state,
                                    onRefresh = {
                                        favouritesViewModel.updateFavouritesList(
                                            convertViewModel.fromSelectedCurrencyCode.value
                                        )
                                    }) {
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
                                                onCompareButtonClick = { compareViewModel.onCompareButtonClick() },
                                                isConvertingLoading = convertViewModel.loading.collectAsState().value,
                                                isComparingLoading = compareViewModel.loading.collectAsState().value
                                            )
                                        }
                                        item {
                                            FavouritesList(
                                                sheetVisibility = favouritesViewModel.dialogVisibility.value,
                                                onIconClick = { favouritesViewModel.onAddFavouritesClick() },
                                                favouriteCurrenciesList = favouritesViewModel.favouritesList.value,
                                                currenciesList = convertViewModel.currenciesList.value,
                                                onItemSelection = { code, name, flag ->
                                                    favouritesViewModel.onItemSelect(
                                                        code,
                                                        name,
                                                        flag
                                                    )
                                                },
                                                onCloseIconClick = { favouritesViewModel.onCloseIconClick() },
                                                onSheetDismissRequest = { favouritesViewModel.onSheetDismissRequest() },
                                                isItemSelected = {
                                                    favouritesViewModel.isItemSelected(
                                                        it
                                                    )
                                                },
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
        }
    }
}




