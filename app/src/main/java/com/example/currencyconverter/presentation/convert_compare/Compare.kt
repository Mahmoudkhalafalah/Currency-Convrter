package com.example.currencyconverter.presentation.convert_compare

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.currencyconverter.R
import com.example.currencyconverter.data.data_source.model.currencies.Data
import com.example.currencyconverter.presentation.commoncomponents.DropDownMenu
import com.example.currencyconverter.presentation.commoncomponents.InputTextField
import com.example.currencyconverter.presentation.commoncomponents.PoppinsFontText

@Composable
fun CompareCard(
    currenciesList: List<Data>,
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
    isComparingLoading : Boolean
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.waiting)
    )
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
                    text = stringResource(R.string.amount),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                InputTextField(
                    text = compareInputAmount,
                    onTextChange = { onCompareInputTextChange(it) })
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {

                PoppinsFontText(text = stringResource(R.string.from))
                Spacer(modifier = Modifier.height(16.dp))
                DropDownMenu(
                    currenciesList = currenciesList,
                    isExpanded = isCompareFromMenuExpanded,
                    selectedCurrencyCode = fromSelectedCompareCode,
                    selectedCurrencyFlag = fromSelectedCompareFlag,
                    onDropDownIconClick = { onFromDropDownCompareIconClick() },
                    onDropDownMenuDismissRequest = { onCompareDropDownMenusDismissRequest() },
                    onItemSelected = { code, flag -> onFromCompareItemSelected(code, flag) }
                )
            }
        }
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
                    text = stringResource(R.string.targeted_currency),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                DropDownMenu(
                    currenciesList = currenciesList,
                    isExpanded = isFirstTargetMenuCompareExpanded,
                    selectedCurrencyCode = firstTargetCompareCode,
                    selectedCurrencyFlag = firstTargetCompareFlag,
                    onDropDownIconClick = { onFirstTargetDropDownCompareIconClick() },
                    onDropDownMenuDismissRequest = { onCompareDropDownMenusDismissRequest() },
                    onItemSelected = { code, flag -> onFirstTargetCompareItemSelected(code, flag) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                InputTextField(
                    text = firstTarget,
                    onTextChange = {},
                    readOnly = true
                )

            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                PoppinsFontText(
                    text = stringResource(R.string.targeted_currency),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                DropDownMenu(
                    currenciesList = currenciesList,
                    isExpanded = isSecondTargetMenuCompareExpanded,
                    selectedCurrencyCode = secondTargetCompareCode,
                    selectedCurrencyFlag = secondTargetCompareFlag,
                    onDropDownIconClick = { onSecondTargetDropDownCompareIconClick() },
                    onDropDownMenuDismissRequest = { onCompareDropDownMenusDismissRequest() },
                    onItemSelected = { code, flag ->
                        onSecondTargetCompareItemSelected(
                            code,
                            flag
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                InputTextField(
                    text = secondTarget,
                    onTextChange = {},
                    readOnly = true
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        AnimatedVisibility(visible = isComparingLoading) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = LottieConstants.IterateForever,
                    isPlaying = true,
                    speed = 1f,
                    restartOnPlay = true
                )
                LottieAnimation(
                    composition,
                    progress,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    onCompareButtonClick()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(size = 20.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF363636)),
                enabled = isComparingLoading.not()
            )
            {
                PoppinsFontText(text = stringResource(R.string.compare), color = Color.White)
            }
        }
    }
}