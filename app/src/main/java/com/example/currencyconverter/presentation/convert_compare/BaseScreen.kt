package com.example.currencyconverter.presentation.convert_compare


import androidx.compose.animation.AnimatedContent
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.currencyconverter.R
import com.example.currencyconverter.data.data_source.model.currencies.Data
import com.example.currencyconverter.presentation.commoncomponents.PoppinsFontText

@Composable
fun Header(
    compareButtonClicked: Boolean,
    onCompareToggleButtonClick: () -> Unit,
    convertButtonClicked: Boolean,
    onConvertToggleButtonClick: () -> Unit,
    onLanguageButtonClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)

    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            val (box, row) = createRefs()
            Box(modifier = Modifier.constrainAs(box) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
                Image(
                    painter = painterResource(id = R.drawable.mainimage),
                    contentDescription = "main background",
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight(),
                    contentScale = ContentScale.FillBounds
                )
                Image(
                    painter = painterResource(id = R.drawable.concurrencyimage),
                    contentDescription = "concurrency",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .width(200.dp)
                        .height(84.dp)
                        .padding(top = 32.dp, start = 28.dp)
                )
                TextButton(
                    onClick = { onLanguageButtonClick() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    PoppinsFontText(text = stringResource(R.string.en_ar), color = Color.White)
                }
                Text(
                    text = stringResource(R.string.currency_converter),
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
                    text = stringResource(R.string.check_live_foreign_currency_exchange_rates),
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
                        color = Color(0xFFF8F8F8), shape = RoundedCornerShape(28.dp)
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
                        onConvertToggleButtonClick()
                    }, modifier = if (convertButtonClicked) {
                        Modifier
                            .background(
                                color = Color.White, shape = RoundedCornerShape(26.dp)
                            )
                            .padding(horizontal = 24.dp)

                    } else Modifier.background(color = Color(0xFFF8F8F8))
                ) {
                    Text(
                        text = stringResource(R.string.convert),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        color = Color(0xFF141414),

                        )
                }
                TextButton(
                    onClick = { onCompareToggleButtonClick() },
                    modifier = if (compareButtonClicked) {
                        Modifier
                            .background(
                                color = Color.White, shape = RoundedCornerShape(24.dp)
                            )
                            .padding(horizontal = 16.dp)
                    } else Modifier.background(color = Color(0xFFF8F8F8))

                ) {

                    Text(
                        text = stringResource(R.string.compare),
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
}

@Composable
fun Main(
    currenciesList: List<Data>,
    convertToggleButtonClicked: Boolean,
    compareToggleButtonClicked: Boolean,
    convertButtonClick: () -> Unit,
    isConvertToMenuExpanded: Boolean,
    convertToSelectedCurrencyCode: String,
    convertToSelectedCurrencyFlag: String,
    onConvertToDropDownIconClick: () -> Unit,
    onConvertDropDownMenusDismissRequest: () -> Unit,
    onConvertToItemSelected: (String, String) -> Unit,
    isConvertFromMenuExpanded: Boolean,
    convertFromSelectedCurrencyCode: String,
    convertFromSelectedCurrencyFlag: String,
    onConvertFromDropDownIconClick: () -> Unit,
    onConvertFromItemSelected: (String, String) -> Unit,
    inputConvertAmount: String,
    convertedAmount: String,
    onConvertInputTextChange: (String) -> Unit,
    isCompareFromMenuExpanded: Boolean,
    isFirstTargetMenuCompareExpanded: Boolean,
    isSecondTargetMenuCompareExpanded: Boolean,
    fromSelectedCompareCode: String,
    fromSelectedCompareFlag: String,
    firstTargetCompareCode: String,
    firstTargetCompareFlag: String,
    secondTargetCompareCode: String,
    secondTargetCompareFlag: String,
    onFromDropDownCompareIconClick: () -> Unit,
    onFirstTargetDropDownCompareIconClick: () -> Unit,
    onSecondTargetDropDownCompareIconClick: () -> Unit,
    onCompareDropDownMenusDismissRequest: () -> Unit,
    onFromCompareItemSelected: (String, String) -> Unit,
    onFirstTargetCompareItemSelected: (String, String) -> Unit,
    onSecondTargetCompareItemSelected: (String, String) -> Unit,
    compareInputAmount: String,
    firstTarget: String,
    secondTarget: String,
    onCompareInputTextChange: (String) -> Unit,
    onCompareButtonClick: () -> Unit,
    isConvertingLoading: Boolean,
    isComparingLoading: Boolean,
) {
    Column(modifier = Modifier.background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {

            AnimatedContent(
                targetState = compareToggleButtonClicked,
                modifier = Modifier.fillMaxWidth(),
                content = { compareButtonClicked ->

                    if (compareButtonClicked) {
                        CompareCard(
                            currenciesList = currenciesList,
                            firstTargetCompareCode = firstTargetCompareCode,
                            isCompareFromMenuExpanded = isCompareFromMenuExpanded,
                            isFirstTargetMenuCompareExpanded = isFirstTargetMenuCompareExpanded,
                            isSecondTargetMenuCompareExpanded = isSecondTargetMenuCompareExpanded,
                            onFromDropDownCompareIconClick = { onFromDropDownCompareIconClick() },
                            firstTargetCompareFlag = firstTargetCompareFlag,
                            fromSelectedCompareCode = fromSelectedCompareCode,
                            onSecondTargetCompareItemSelected = { code, flag ->
                                onSecondTargetCompareItemSelected(
                                    code, flag
                                )
                            },
                            fromSelectedCompareFlag = fromSelectedCompareFlag,
                            secondTargetCompareCode = secondTargetCompareCode,
                            secondTargetCompareFlag = secondTargetCompareFlag,
                            onSecondTargetDropDownCompareIconClick = onSecondTargetDropDownCompareIconClick,
                            firstTarget = firstTarget,
                            compareInputAmount = compareInputAmount,
                            onCompareDropDownMenusDismissRequest = onCompareDropDownMenusDismissRequest,
                            onFirstTargetCompareItemSelected = onFirstTargetCompareItemSelected,
                            onFirstTargetDropDownCompareIconClick = onFirstTargetDropDownCompareIconClick,
                            onFromCompareItemSelected = onFromCompareItemSelected,
                            onCompareInputTextChange = onCompareInputTextChange,
                            secondTarget = secondTarget,
                            onCompareButtonClick = { onCompareButtonClick() },
                            isComparingLoading = isComparingLoading
                        )
                    }
                    if (convertToggleButtonClicked) {
                        ConvertCard(
                            currenciesList = currenciesList,
                            onConvertButtonClick = { convertButtonClick() },
                            isToExpanded = isConvertToMenuExpanded,
                            toSelectedCurrencyCode = convertToSelectedCurrencyCode,
                            toSelectedCurrencyFlag = convertToSelectedCurrencyFlag,
                            onToDropDownIconClick = { onConvertToDropDownIconClick() },
                            onDropDownMenuDismissRequest = { onConvertDropDownMenusDismissRequest() },
                            onToItemSelected = { code, flag ->
                                onConvertToItemSelected(
                                    code, flag
                                )
                            },
                            isFromExpanded = isConvertFromMenuExpanded,
                            fromSelectedCurrencyCode = convertFromSelectedCurrencyCode,
                            fromSelectedCurrencyFlag = convertFromSelectedCurrencyFlag,
                            onFromDropDownIconClick = { onConvertFromDropDownIconClick() },
                            onFromItemSelected = { code, flag ->
                                onConvertFromItemSelected(
                                    code, flag
                                )
                            },
                            amount = inputConvertAmount,
                            convertedAmount = convertedAmount,
                            onInputTextChange = { onConvertInputTextChange(it) },
                            isConvertingLoading = isConvertingLoading
                        )
                    }
                },
                transitionSpec = {
                    slideInHorizontally(initialOffsetX = {
                        if (compareToggleButtonClicked) it else -it
                    }) togetherWith slideOutHorizontally(targetOffsetX = {
                        if (compareToggleButtonClicked) -it else it
                    })
                },
                label = ""
            )
        }
    }
}


