package com.example.currencyconverter.presentation.edit_show_favourites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.model.Currency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteCurrenciesSelectionDisplay(
    onDialogCloseClick: () -> Unit,

    currenciesList: List<Currency>,
    onItemSelection: (Int, Boolean) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {

        }
    ) {


        Card(
            modifier = Modifier
                .fillMaxWidth(),

            shape = RoundedCornerShape(20.dp),


            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8F8F8))
                    .padding(16.dp),
            ) {
                IconButton(
                    onClick = { onDialogCloseClick() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "")

                }
                FavouritesSelectionColumn(
                    currenciesList = currenciesList,
                    onItemSelection = { id, state -> onItemSelection(id, state) })
            }
        }
    }


}


@Composable
fun FavouritesSelectionColumn(
    currenciesList: List<Currency>,
    onItemSelection: (Int, Boolean) -> Unit,
) {

    LazyColumn {
        items(currenciesList) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.united_states/*it.flag*/),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .width(48.dp)
                            .height(48.dp)
                    )
                    Column {
                        Text(
                            text = it.currencyCode, modifier = Modifier.padding(12.dp)
                        )
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = "currency",
                            color = Color.LightGray
                        )
                    }
                }


                Checkbox(
                    checked = it.isSelected,
                    onCheckedChange = { state -> onItemSelection(it.id, state) })

            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .alpha(0.3f)
            )
        }
    }

}
