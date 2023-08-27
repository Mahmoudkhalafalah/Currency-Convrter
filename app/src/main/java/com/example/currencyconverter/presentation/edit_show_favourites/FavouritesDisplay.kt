package com.example.currencyconverter.presentation.edit_show_favourites


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.model.Currency


@Composable
fun FavouritesList(
    sheetVisibility: Boolean,
    onIconClick: () -> Unit,
    favouriteCurrenciesList: List<Currency>,
    currenciesList: List<Currency>,
    onItemSelection: (Int, Boolean) -> Unit,
    onCloseIconClick: () -> Unit,
    onSheetDismissRequest: () -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
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
        FavouriteCurrenciesListDisplay(favouriteCurrenciesList)


    }

    AnimatedVisibility(visible = sheetVisibility, enter = fadeIn()) {

        FavouriteCurrenciesSelectionDisplay(
            currenciesList = currenciesList,
            onCloseIconClick = { onCloseIconClick() },

            onItemSelection = { id, state -> onItemSelection(id, state) },
            onSheetDismissRequest = { onSheetDismissRequest() }
        )
    }

}

@Composable
fun FavouriteCurrenciesListDisplay(
    favouriteCurrenciesList: List<Currency>,
) {


    LazyColumn {
        items(favouriteCurrenciesList) {
            ListItem(
                headlineContent = {
                    Text(
                        text = "USD", style = TextStyle(
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
                        text = "Currency",
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
                    Image(
                        painter = painterResource(id = R.drawable.united_states),
                        contentDescription = it.currencyCode,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .width(48.dp)
                            .height(48.dp)
                    )
                },
                trailingContent = {
                    Text(
                        text = it.currencyRate.toString(),
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


/*
 Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.united_states),
                        contentDescription = it.currencyCode,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .width(48.dp)
                            .height(48.dp)
                    )
                    Column {
                        Text(
                            text = it.currencyCode, style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.montserratthin)),
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF121212),
                            )
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "Currency",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.montserratthin)),
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFB8B8B8),
                            )
                        )
                    }
                }



                Text(
                    text = it.currencyRate.toString(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.montserratmedium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF121212)
                    )
                )


            }
 */


