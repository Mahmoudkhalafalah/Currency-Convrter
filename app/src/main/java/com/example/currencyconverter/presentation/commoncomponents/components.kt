package com.example.currencyconverter.presentation.commoncomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.currencyconverter.R
import com.example.currencyconverter.data.data_source.model.currencies.Data

@Composable
fun InputTextField(
    text: String,
    onTextChange: (String) -> Unit,
    readOnly: Boolean = false,
) {
    OutlinedTextField(
        value = text,
        shape = RoundedCornerShape(25.dp),
        placeholder = { Text(text = "1") },
        onValueChange = {
            onTextChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = Color(0xFFC5C5C5),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .height(54.dp)
            .fillMaxWidth()
            .background(
                color = Color(0xFFF9F9F9),
                shape = RoundedCornerShape(size = 20.dp)
            ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Unspecified,
            unfocusedBorderColor = Color.Unspecified,
        ),
        readOnly = readOnly
    )
}

@Composable
fun PoppinsFontText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    font: Int = 14,
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = font.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
        ), modifier = modifier,
        color = color
    )
}


@Composable
fun DropDownMenu(
    currenciesList: List<Data>,
    isExpanded: Boolean,
    selectedCurrencyCode: String,
    selectedCurrencyFlag: String,
    onDropDownIconClick: () -> Unit,
    onDropDownMenuDismissRequest: () -> Unit,
    onItemSelected: (String, String) -> Unit,
) {
    val icon = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = Color(0xFFC5C5C5),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .padding(0.5.dp)
            .height(54.dp)
            .fillMaxWidth()
            .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp))
            .clickable { onDropDownIconClick() }
    ) {
        AsyncImage(
            model = selectedCurrencyFlag,
            contentDescription = "flag",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 11.dp)
                .width(28.dp)
                .height(20.dp)
        )
        Text(
            text = selectedCurrencyCode,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight.ExtraLight,
                color = Color.Black,
            ),
            modifier = Modifier
                .weight(0.8f)
                .padding(start = 8.dp)
        )
        IconButton(
            onClick = { onDropDownIconClick() },
            modifier = Modifier
                .padding(end = 16.dp)
                .size(32.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "spinner icon",
                modifier = Modifier
                    .padding(1.dp),
                tint = Color.Black
            )
        }
        DropdownMenu(
            expanded = isExpanded,
            modifier = Modifier
                .height(200.dp)
                .background(color = Color(0xFFF9F9F9))
                .fillMaxWidth(0.47f)
                .clip(RoundedCornerShape(16.dp)),
            onDismissRequest = { onDropDownMenuDismissRequest() }
        ) {
            currenciesList.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.code,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight.ExtraLight,
                                color = Color.Black
                            )
                        )
                    },
                    leadingIcon = {
                        AsyncImage(
                            model = it.flagUrl,
                            contentDescription = "flag",
                            modifier = Modifier
                                .width(28.dp)
                                .height(20.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    },
                    onClick = {
                        onItemSelected(it.code, it.flagUrl)
                    }
                )
            }
        }
    }
}