package com.example.currencyconverter.presentation.upperUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.data.data_source.model.currencies.Data
import com.example.currencyconverter.presentation.commoncomponents.DropDownMenu
import com.example.currencyconverter.presentation.commoncomponents.InputTextField
import com.example.currencyconverter.presentation.commoncomponents.PoppinsFontText


@Composable
fun CurrencyCard(
    currenciesList: List<Data>,
    onConvertButtonClick: () -> Unit,
    isToExpanded: Boolean,
    toSelectedCurrencyCode: String,
    toSelectedCurrencyFlag: String,
    onToDropDownIconClick: () -> Unit,
    onDropDownMenuDismissRequest: () -> Unit,
    onToItemSelected: (String, String) -> Unit,
    isFromExpanded: Boolean,
    fromSelectedCurrencyCode: String,
    fromSelectedCurrencyFlag: String,
    onFromDropDownIconClick: () -> Unit,
    onFromItemSelected: (String, String) -> Unit,
    amount: String,
    convertedAmount: String,
    onInputTextChange: (String) -> Unit,

    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 32.dp, end = 32.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {

                PoppinsFontText(
                    text = "Amount",
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                InputTextField(text = amount, onTextChange = { onInputTextChange(it) })
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1.5f)
            ) {

                PoppinsFontText(text = "From")
                Spacer(modifier = Modifier.height(16.dp))
                DropDownMenu(
                    currenciesList = currenciesList,
                    isExpanded = isFromExpanded,
                    selectedCurrencyCode = fromSelectedCurrencyCode,
                    selectedCurrencyFlag = fromSelectedCurrencyFlag,
                    onDropDownIconClick = { onFromDropDownIconClick() },
                    onDropDownMenuDismissRequest = { onDropDownMenuDismissRequest() },
                    onItemSelected = { code, flag -> onFromItemSelected(code, flag) }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1.5f)
            ) {
                PoppinsFontText(text = "To")
                Spacer(modifier = Modifier.height(16.dp))

                DropDownMenu(
                    currenciesList = currenciesList,
                    isExpanded = isToExpanded,
                    selectedCurrencyCode = toSelectedCurrencyCode,
                    selectedCurrencyFlag = toSelectedCurrencyFlag,
                    onDropDownIconClick = { onToDropDownIconClick() },
                    onDropDownMenuDismissRequest = { onDropDownMenuDismissRequest() },
                    onItemSelected = { code, flag -> onToItemSelected(code, flag) }
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                PoppinsFontText(text = "Amount", modifier = Modifier.padding(bottom = 16.dp))

                InputTextField(
                    text = convertedAmount,
                    onTextChange = {},
                    readOnly = true
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {


            Button(
                onClick = { onConvertButtonClick() }, modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(size = 20.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF363636))
            )
            {
                PoppinsFontText(text = "Convert", color = Color.White)
            }
        }
    }
}

