@file:OptIn(ExperimentalAnimationApi::class)

package com.example.currencyconverter.presentation.upperUi


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.currencyconverter.R
import com.example.currencyconverter.data.data_source.model.currencies.Data


@Composable
fun Main(
    compareButtonClicked: Boolean,
    onCompareButtonClick: () -> Unit,
    convertButtonClicked: Boolean,
    onConvertButtonClick: () -> Unit,
    currenciesList: List<Data>,
    convertButtonClick: () -> Unit,
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
    convertedAmount:String,
    onConvertInputTextChange:(String)->Unit
) {
    Column(modifier = Modifier.background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2715f)

        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            ) {
                val (box, row, card) = createRefs()

                Box(modifier = Modifier.constrainAs(box) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.mainimage),
                        contentDescription = "null",
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxHeight()
                    )
                    Image(
                        painter = painterResource(id = R.drawable.concurrencyimage),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .width(200.dp)
                            .height(84.dp)
                            .padding(top = 32.dp, start = 28.dp)
                    )
                    Text(
                        text = "Currency Converter",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 32.dp),
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.montserratmedium)),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF),
                        )
                    )
                    Text(
                        text = "Check live foreign currency exchange rates",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 64.dp),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserratthin)),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF),
                        )
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(64.dp)
                        .background(
                            color = Color(0xFFF8F8F8),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .constrainAs(row) {
                            top.linkTo(box.bottom)
                            bottom.linkTo(box.bottom)
                            start.linkTo(box.start)
                            end.linkTo(box.end)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {
                    TextButton(
                        onClick = {
                            onConvertButtonClick()
                        }, modifier = if (convertButtonClicked) {
                            Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(26.dp)
                                )
                                .padding(horizontal = 24.dp)

                        } else Modifier.background(color = Color(0xFFF8F8F8))
                    ) {
                        Text(
                            text = "Convert",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            color = Color(0xFF141414),

                            )
                    }
                    TextButton(
                        onClick = { onCompareButtonClick() },
                        modifier = if (compareButtonClicked) {
                            Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .padding(horizontal = 16.dp)
                        } else Modifier.background(color = Color(0xFFF8F8F8))

                    ) {

                        Text(
                            text = "Compare",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            color = Color(0xFF141414)
                        )

                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {

            AnimatedContent(
                targetState = compareButtonClicked, modifier = Modifier
                    .fillMaxWidth(),
                content = { compareButtonClicked ->

                    if (compareButtonClicked) {

                        Compare()

                    }
                    if (convertButtonClicked) {
                        CurrencyCard(
                            currenciesList = currenciesList,
                            onConvertButtonClick = { convertButtonClick() },
                            isToExpanded = isToExpanded,
                            toSelectedCurrencyCode = toSelectedCurrencyCode,
                            toSelectedCurrencyFlag = toSelectedCurrencyFlag,
                            onToDropDownIconClick = { onToDropDownIconClick() },
                            onDropDownMenuDismissRequest = { onDropDownMenuDismissRequest() },
                            onToItemSelected = { code, flag -> onToItemSelected(code, flag) },
                            isFromExpanded = isFromExpanded,
                            fromSelectedCurrencyCode = fromSelectedCurrencyCode,
                            fromSelectedCurrencyFlag = fromSelectedCurrencyFlag,
                            onFromDropDownIconClick = { onFromDropDownIconClick() },
                            onFromItemSelected = { code, flag -> onFromItemSelected(code, flag) },
                            amount =amount,
                            convertedAmount = convertedAmount,
                            onInputTextChange = {onConvertInputTextChange(it)}
                        )
                    }

                }, transitionSpec = {
                    slideInHorizontally(
                        initialOffsetX = {
                            if (compareButtonClicked) it else -it
                        }
                    ) togetherWith slideOutHorizontally(
                        targetOffsetX = {
                            if (compareButtonClicked) -it else it
                        }
                    )

                }, label = ""
            )
        }


    }
    Box(modifier = Modifier.fillMaxHeight(1f)) {

    }
}


