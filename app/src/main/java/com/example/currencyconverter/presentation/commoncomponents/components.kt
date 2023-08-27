package com.example.currencyconverter.presentation.commoncomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.currencyconverter.R

@Composable
fun InputTextField(
    text: String,
    onTextChange: (String) -> Unit,
    width: Dp,
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

            .width(width)
            .height(54.dp)
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

data class Currency(val currencyCode: String, val currencyName: String, val currencyFlag: String)

val currenciesList = listOf(
    Currency(
        currencyCode = "EGP",
        currencyName = "Egyptian Pound",
        currencyFlag = "https://cdn.britannica.com/85/185-004-1EA59040/Flag-Egypt.jpg"
    ),
    Currency(
        currencyCode = "USD",
        currencyName = "US Dollar",
        currencyFlag = "https://cdn.britannica.com/79/4479-050-6EF87027/flag-Stars-and-Stripes-May-1-1795.jpg"
    ),
    Currency(
        currencyCode = "EUR",
        currencyName = "Euro",
        currencyFlag = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Flag_of_Europe.svg/2560px-Flag_of_Europe.svg.png"
    ),
    Currency(
        currencyCode = "GBP",
        currencyName = "Sterling Pound",
        currencyFlag = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/1280px-Flag_of_the_United_Kingdom_%283-5%29.svg.png"
    ),
    Currency(
        currencyCode = "AED",
        currencyName = "UAE Dirham",
        currencyFlag = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/Flag_of_the_United_Arab_Emirates.svg/1280px-Flag_of_the_United_Arab_Emirates.svg.png"
    ),
    Currency(
        currencyCode = "JPY",
        currencyName = "Japan Yen",
        currencyFlag = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Flag_of_Japan.svg/1280px-Flag_of_Japan.svg.png"
    ),
    Currency(
        currencyCode = "SAR",
        currencyName = "Saudi Riyal",
        currencyFlag = "https://cdn.britannica.com/79/5779-004-DC479508/Flag-Saudi-Arabia.jpg"
    ),
    Currency(
        currencyCode = "KWD",
        currencyName = "Kuwait Dinar",
        currencyFlag = "https://cdn.britannica.com/70/5770-004-A99DD01D/Flag-Kuwait.jpg"
    )
)

@Composable
fun DropDownMenu(width: Dp) {

    var isExpanded by remember { mutableStateOf(false) }

    var selectedCurrencyCode by remember { mutableStateOf("EGP") }
    var selectedCurrencyFlag by remember { mutableStateOf("https://cdn.britannica.com/85/185-004-1EA59040/Flag-Egypt.jpg") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFC5C5C5),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .padding(0.5.dp)
            .height(54.dp)
            .width(width)
            .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp))
            .clickable { isExpanded = isExpanded.not() }
    ) {
        // The flag of the currency
        AsyncImage(
            model = selectedCurrencyFlag,
            contentDescription = "currency flag",
            /*placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),*/
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 11.dp)
                .width(28.dp)
                .height(20.dp)
        )

        Text(
            text = selectedCurrencyCode,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color.Black,
            ),
            modifier = Modifier
                .weight(0.8f)
                .padding(start = 8.dp)
        )
        IconButton(
            onClick = { isExpanded = isExpanded.not() },
            modifier = Modifier
                .padding(end = 16.dp)
                .size(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                contentDescription = "Show all currencies",
                modifier = Modifier
                    .padding(1.dp)
                    .size(16.dp),
                tint = Color.Black
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            modifier = Modifier
                .height(250.dp)
                .background(color = Color(0xFFF9F9F9)),
            onDismissRequest = { isExpanded = isExpanded.not() }
        ) {
            repeat(currenciesList.size) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = currenciesList[it].currencyCode,
                            style = TextStyle(
                                fontSize = 16.sp,
                                /*fontFamily = FontFamily(Font(R.font.open sans)),*/
                                fontWeight = FontWeight(400),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    },
                    leadingIcon = {
                        AsyncImage(
                            model = currenciesList[it].currencyFlag,
                            /*placeholder = painterResource(id = R.drawable.placeholder),
                            error = painterResource(id = R.drawable.placeholder),*/
                            contentDescription = "Currency Flag",
                            modifier = Modifier
                                .width(28.dp)
                                .height(20.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    },
                    onClick = {
                        selectedCurrencyCode = currenciesList[it].currencyCode
                        selectedCurrencyFlag = currenciesList[it].currencyFlag
                        isExpanded = isExpanded.not()
                    }
                )
            }
        }
    }
}