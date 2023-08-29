package com.example.currencyconverter.presentation.edit_show_favourites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.currencyconverter.R
import com.example.currencyconverter.data.data_source.model.currencies.Data
import com.example.currencyconverter.presentation.commoncomponents.PoppinsFontText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteCurrenciesSelectionDisplay(
    onCloseIconClick: () -> Unit,
    currenciesList: List<Data>,
    onItemSelection: (String, String, String) -> Unit,
    onSheetDismissRequest: () -> Unit,
    isItemSelected: (String) -> Boolean,
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onSheetDismissRequest() },
        containerColor = Color.White
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
            ) {
                IconButton(
                    onClick = { onCloseIconClick() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                }
                FavouritesSelectionColumn(
                    currenciesList = currenciesList,
                    onItemSelection = { code, name, flag -> onItemSelection(code, name, flag) },
                    isItemSelected = { isItemSelected(it) }
                )
            }
        }
    }


}

@Composable
fun FavouritesSelectionColumn(
    currenciesList: List<Data>,
    onItemSelection: (String, String, String) -> Unit,
    isItemSelected: (String) -> Boolean,
) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color(0xFFF8F8F8))
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        PoppinsFontText(
            text = stringResource(R.string.my_favourites),
            font = 18,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .background(Color(0xFFF8F8F8))
                .padding(horizontal = 8.dp)
                .height(1000.dp)
        ) {
            items(currenciesList) {
                ListItem(
                    headlineContent = {
                        Text(
                            text = it.code, style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight.Light,
                                color = Color(0xFF121212),
                            )
                        )
                    },
                    supportingContent = {
                        Text(
                            text = it.name,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight.Thin,
                                color = Color(0xFFB8B8B8),
                            )
                        )
                    },
                    leadingContent = {
                        AsyncImage(model = it.flagUrl, contentDescription = "", modifier = Modifier
                            .padding(end = 8.dp)
                            .size(48.dp)
                            .clip(CircleShape),
                            contentScale = ContentScale.FillBounds
                        )

                    },
                    trailingContent = {
                        IconButton(onClick = { onItemSelection(it.code, it.name, it.flagUrl) }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = if (isItemSelected(it.code)) R.drawable.baseline_check_circle_24 else R.drawable.outline_circle_24),
                                contentDescription = "check icon"
                            )
                        }
                    },
                    colors = ListItemDefaults.colors((Color(0xFFF8F8F8)))
                )
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .alpha(0.3f)
                )
            }
        }
    }
}


