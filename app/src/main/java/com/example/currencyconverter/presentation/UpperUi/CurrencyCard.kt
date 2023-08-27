package com.example.currencyconverter.presentation.upperUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.presentation.commoncomponents.DropDownMenu
import com.example.currencyconverter.presentation.commoncomponents.InputTextField
import com.example.currencyconverter.presentation.commoncomponents.PoppinsFontText


@Composable
fun CurrencyCard() {
    var text by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
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
                horizontalAlignment = Alignment.Start
            ) {

                PoppinsFontText(
                    text = "Amount",
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                InputTextField(text = text, onTextChange = { text = it }, width = 121.dp)
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {

                PoppinsFontText(text = "From")
                DropDownMenu(184.dp)
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
                horizontalAlignment = Alignment.Start
            ) {
                PoppinsFontText(text = "To")

                DropDownMenu(184.dp)
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                PoppinsFontText(text = "Amount", modifier = Modifier.padding(bottom = 16.dp))

                InputTextField(
                    text = text2,
                    onTextChange = { text2 = it },
                    width = 121.dp,
                    readOnly = true
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {


            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
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

