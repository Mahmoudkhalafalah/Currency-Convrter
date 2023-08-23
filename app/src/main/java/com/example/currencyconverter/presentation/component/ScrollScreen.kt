package com.example.currencyconverter.presentation.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card

import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview


import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.currencyconverter.R


@Composable
fun FavouriteComponent() {
  var isClicked by remember { mutableStateOf(false) }
    Column {
       Row (modifier = Modifier
           .fillMaxWidth()
           .padding(16.dp),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically){
           Text(text = "live exchange rates"
            )
           OutlinedButton(onClick = {isClicked=true
           }
          ) {
               Icon(imageVector = Icons.Default.AddCircle, contentDescription =""
                 ,tint= Color.Black,
                   modifier = Modifier.padding(6.dp)
               )
               Text(text = "Add to Favourite",
                   color = Color.Black
               )

           }

           
       }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "My Prtofiol",modifier = Modifier.padding(12.dp)
            , style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))
            BottomListFavourite()


    }
    if (isClicked){
       FavouriteDialog()
    }

}

@Preview(showBackground = true)
@Composable
fun ListDialog(){
    val currencies = listOf(
        IconsFlag(R.drawable.flag, "EUR","$123,435,34"),
        IconsFlag(R.drawable.flag, "EUR","$124,543,1"),
        IconsFlag(R.drawable.flag, "EUR","$32,45,234"),
        IconsFlag(R.drawable.flag, "EUR","$12,345,66"),
        IconsFlag(R.drawable.flag, "EUR","$12,345,66"),
        IconsFlag(R.drawable.flag, "EUR","$12,345,66"),
    )
    val iconsFlag =IconsFlag(R.drawable.flag, "USD","$12,345,66")
   var ischecked by remember { mutableStateOf(false) }

    LazyColumn {
        items(currencies.size) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row{

                    Image(
                        painter = painterResource(id = iconsFlag.flagId),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(60.dp)
                    )
                    Column {
                        Text(
                            text = iconsFlag.name
                            , modifier = Modifier.padding(12.dp)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "currency",
                            color = Color.LightGray
                        )
                    }
                }


                Checkbox(checked = ischecked, onCheckedChange = { ischecked=it } )

            }
            Divider(modifier = Modifier.height(1.dp))
        }
    }

}
@Composable
fun BottomListFavourite() {
    val currencies = listOf(
        IconsFlag(R.drawable.flag, "EUR","$123,435,34"),
        IconsFlag(R.drawable.flag, "EUR","$124,543,1"),
        IconsFlag(R.drawable.flag, "EUR","$32,45,234"),
        IconsFlag(R.drawable.flag, "EUR","$12,345,66"),
    )

       LazyColumn {
        items(currencies.size) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row{

                    Image(
                        painter = painterResource(id = R.drawable.flag),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(60.dp)
                    )
                    Column {
                        Text(
                            text = "USD"
                            , modifier = Modifier.padding(12.dp)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "currency",
                            color = Color.LightGray
                        )
                    }
                }
                Text(text = "$123,345,33"
                    , style = MaterialTheme.typography.titleLarge)

            }
            Divider(modifier = Modifier.height(1.dp))
        }
    }


}
@Composable
fun FavouriteDialog(){
   var ischeckedclear by remember { mutableStateOf(false) }
  Dialog(onDismissRequest = {

  }) {

   Card(
       modifier = Modifier
           .fillMaxWidth()
           .padding(16.dp),
       shape = RoundedCornerShape(15.dp),


   ) {
      Column (
          Modifier
              .fillMaxWidth()
              .background(Color.White)){
              IconButton(onClick = { ischeckedclear=true }) {
              Icon(imageVector = Icons.Default.Clear, contentDescription = "")
              
          }
        ListDialog()
      }
   }



  }
}




data class IconsFlag(val flagId:Int,val name:String
,val amount:String)