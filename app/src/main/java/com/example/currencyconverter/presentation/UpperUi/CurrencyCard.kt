package com.example.currencyconverter.presentation.UpperUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CurrencyCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp).background(color = Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {

           Row(
               modifier = Modifier.fillMaxWidth().background(color = Color.White) ,
           ) {
               Text(text = "Ammount",fontWeight = FontWeight(600),
                   color = Color(0xFF000000),fontSize = 14.sp,)
               Spacer(modifier = Modifier.width(100.dp))
               Text(text = "From",fontWeight = FontWeight(600),
                   color = Color(0xFF000000),fontSize = 14.sp,)
           }

            var text by remember { mutableStateOf("") }
            var text2 by remember { mutableStateOf("") }
            Row(
                modifier = Modifier.fillMaxWidth().background(color = Color.White) , horizontalArrangement = Arrangement.SpaceBetween
            ) {
               OutlinedTextField(
                    value = text,
                    shape = RoundedCornerShape(25.dp),
                   placeholder = { Text(text = "1")},
                    onValueChange = { newValue ->
                        text = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.width(130.dp)
                        .padding(16.dp)
                )
                CurrencyDropDown(Modifier.weight(1f))



            }
           //

            Row(
                modifier = Modifier.fillMaxWidth().background(color = Color.White) ,
            ) {
                Text(text = "to",fontWeight = FontWeight(600),
                    color = Color(0xFF000000),fontSize = 14.sp,)
                Spacer(modifier = Modifier.width(200.dp))
                Text(text = "Amount",fontWeight = FontWeight(600),
                    color = Color(0xFF000000),fontSize = 14.sp,)
            }

            Row(modifier = Modifier.fillMaxWidth().background(color = Color.White) , horizontalArrangement = Arrangement.SpaceBetween ) {
                CurrencyDropDown(Modifier.weight(1f))

               OutlinedTextField(
                    value = text2,
                    shape = RoundedCornerShape(25.dp),
                    placeholder = { Text(text = "1")},
                    onValueChange = { newValue ->
                        text2 = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.width(200.dp)
                        .padding(16.dp)

                )

            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color(0xFF363636), shape = RoundedCornerShape(size = 20.dp)))
                 {
                Text(text = "Convert")
            }

        }
    }

}
