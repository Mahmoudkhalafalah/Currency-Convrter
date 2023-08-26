package com.example.currencyconverter.presentation.UpperUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
@Preview(showBackground = true)
@Composable
fun TopBar() {
    var clicked: Boolean by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),


            ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.mainbackground),
                    contentDescription = "null", modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Text(
                            text = "Currency Converter",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFFFF),
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Currency Converter",
                        fontSize = 20.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Check live foreign curency exhange rates",
                        fontSize = 15.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            10.026662826538086.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .width(200.77539.dp)
                            .height(54.06489.dp)
                            .background(
                                color = Color(0xFFF8F8F8),
                                shape = RoundedCornerShape(size = 22.58325.dp)
                            )
                            .padding(
                                start = 10.02666.dp,
                                top = 10.02666.dp,
                                end = 10.02666.dp,
                                bottom = 10.02666.dp
                            )

                    ) {

                        TextButton(onClick = {
                            clicked=true
                        }) {
                            Text(
                                text = "Convert", fontSize = 13.8.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF141414)
                            )
                        }
                        TextButton(onClick = { clicked = false }) {
                            Text(
                                text = "Compare", fontSize = 13.8.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF141414)
                            )

                        }
                    }
       Box {
           if (clicked ) {
               CurrencyCard()
           } else   {
               //clicked2=false
               Compare()

           }
       }


                }


            }
        }


    }  

}







