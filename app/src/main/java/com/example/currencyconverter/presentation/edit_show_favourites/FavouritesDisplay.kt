package com.example.currencyconverter.presentation.edit_show_favourites


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.currencyconverter.R
import com.example.currencyconverter.data.data_source.model.currencies.Data
import com.example.currencyconverter.domain.model.Currency
import kotlin.math.roundToInt

@Composable
fun FavouritesList(
    sheetVisibility: Boolean,
    onIconClick: () -> Unit,
    favouriteCurrenciesList: List<Currency>,
    favouriteListRates: List<Double>,
    currenciesList: List<Data>,
    onItemSelection: (String, String, String) -> Unit,
    onCloseIconClick: () -> Unit,
    onSheetDismissRequest: () -> Unit,
    isItemSelected: (String) -> Boolean,
) {
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Spacer(modifier = Modifier.height(32.dp))
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .alpha(0.3f),
            color = Color(0xFFB9C1D9)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.live_exchange_rates),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF202020),
                )
            )
            OutlinedButton(
                border = BorderStroke(1.dp, Color.Black), onClick = {
                    onIconClick()
                }, contentPadding = PaddingValues(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(16.dp)
                )
                Text(
                    text = stringResource(R.string.add_to_favourites),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF363636),
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.my_portfolio),
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight.ExtraLight,
                color = Color(0xFF121212),
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        FavouriteCurrenciesListDisplay(favouriteCurrenciesList, favouriteListRates)
    }
    AnimatedVisibility(visible = sheetVisibility, enter = fadeIn()) {
        FavouriteCurrenciesSelectionDisplay(
            currenciesList = currenciesList,
            onCloseIconClick = { onCloseIconClick() },

            onItemSelection = { code, name, flag -> onItemSelection(code, name, flag) },
            onSheetDismissRequest = { onSheetDismissRequest() },
            isItemSelected = { isItemSelected(it) }
        )
    }
}

@Composable
fun FavouriteCurrenciesListDisplay(
    favouriteCurrenciesList: List<Currency>,
    favouriteListRates: List<Double>,
) {
    var iterator = 0
    Column {
        favouriteCurrenciesList.forEach {
            ListItem(
                headlineContent = {
                    Text(
                        text = it.code, style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight.Light,
                            color = Color(0xFF121212),
                        )
                    )
                },
                supportingContent = {
                    Text(
                        text = it.name,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight.ExtraLight,
                            color = Color(0xFFB8B8B8),
                        )
                    )
                },
                leadingContent = {
                    AsyncImage(
                        model = it.flag, contentDescription = it.code, modifier = Modifier
                            .padding(end = 8.dp)
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillHeight
                    )
                },
                trailingContent = {
                    Text(
                        text = (((favouriteListRates[iterator++] * 100).roundToInt()) / 100f).toString(),
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.montserratmedium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF121212)
                        )
                    )
                }
            )
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .alpha(0.3f),
                color = Color(0xFFB9C1D9)
            )
        }
    }
}
