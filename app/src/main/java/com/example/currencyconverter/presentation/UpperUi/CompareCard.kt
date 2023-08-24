package com.example.currencyconverter.presentation.UpperUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
fun Compare(){

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
                modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Ammount",fontWeight = FontWeight(600),
                    color = Color(0xFF000000),fontSize = 14.sp,)

                Text(text = "From",fontWeight = FontWeight(600),
                    color = Color(0xFF000000),fontSize = 14.sp,)
            }

            var text3 by remember { mutableStateOf("") }
            var text4 by remember { mutableStateOf("") }
            var text5 by remember { mutableStateOf("") }

            Row(
                modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = text3,
                    shape = RoundedCornerShape(16.dp),
                    placeholder = { Text(text = "1") },
                    onValueChange = { newValue ->
                        text3 = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .width(100.dp)
                        .padding(16.dp)
                )
                CurrencyDropDown(Modifier.weight(1f))


            }
            Row(
                modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Targeted currency",fontWeight = FontWeight(600),
                    color = Color(0xFF000000),fontSize = 14.sp,)

                Text(text = "Targeted currency",fontWeight = FontWeight(600),
                    color = Color(0xFF000000),fontSize = 14.sp,)
            }




            Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween) {
                CurrencyDropDown(modifier = Modifier.weight(1f))
                CurrencyDropDown(modifier = Modifier.weight(1f))

            }
            Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedTextField(
                    value = text4,
                    shape = RoundedCornerShape(16.dp),
                    placeholder = { Text(text = "1") },
                    onValueChange = { newValue ->
                        text4= newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
                OutlinedTextField(
                    value = text5,
                    shape = RoundedCornerShape(16.dp),
                    placeholder = { Text(text = "1") },
                    onValueChange = { newValue ->
                        text5 = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color(0xFF363636), shape = RoundedCornerShape(size = 20.dp)))
            {
                Text(text = "Compare")
            }

        }
    }

}