package com.example.currencyconverter.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun dropDownMenu1() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
    var selectedText by remember { mutableStateOf("") }


    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }


    Column(
        Modifier
            .wrapContentSize()
            .padding(0.dp,0.dp,20.dp,0.dp)
            .background(color = Color.White)
            .border(
                border = BorderStroke(1.dp, color = Color.DarkGray),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        TextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            ),
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .wrapContentSize(), placeholder = { Text(text = "USD") },
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },

            ) {
            suggestions.forEach { label ->
                DropdownMenuItem({ Text(text = label) }, onClick = {
                    selectedText = label
                    expanded = false
                })


            }
        }

    }
}
