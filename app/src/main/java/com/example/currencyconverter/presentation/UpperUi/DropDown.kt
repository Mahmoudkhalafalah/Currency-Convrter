package com.example.currencyconverter.presentation.UpperUi

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.R

data class DropDownItem(val currencyCode: String, @DrawableRes val flagId: Int)

@Composable
fun CurrencyDropDown(modifier: Modifier) {
    val currencies = listOf(
        DropDownItem("USD", R.drawable.baseline_flag_circle_24),
        DropDownItem("EUR", R.drawable.baseline_flag_circle_24),
        DropDownItem("JPY", R.drawable.baseline_flag_24)
    )

    var isExpanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var selectedCurrency = currencies.getOrNull(selectedIndex)

    Card(modifier=Modifier.width(230.dp)) {
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }.background(color = Color.White)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp).background(color = Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (selectedCurrency != null) {
                Row(
                    modifier = Modifier
                        .padding(16.dp).background(color = Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                        Image(
                            painter = painterResource(id = selectedCurrency.flagId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = selectedCurrency.currencyCode)

                    }
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }

                if (isExpanded) {
                    currencies.forEachIndexed { index, dropDownItem ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex = index
                                isExpanded = false
                            },
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = dropDownItem.flagId),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(25.dp)
                                            .padding(end = 8.dp)
                                    )
                                    Text(text = dropDownItem.currencyCode)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

