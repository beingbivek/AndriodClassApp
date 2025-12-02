package com.example.demoapp

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.demoapp.ui.theme.BgColor
import com.example.demoapp.ui.theme.Blue
import com.example.demoapp.ui.theme.CardBGColor
import com.example.demoapp.ui.theme.PurpleGrey80
import com.example.demoapp.ui.theme.White

@Composable
fun HomeScreen() {
    var search by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor)
            .padding(15.dp)
    ) {
        item {
            TextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text("Search here")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedIndicatorColor = White,
                    unfocusedIndicatorColor = Color.White
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_search_24),
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(CardBGColor)
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(50.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_location_pin_24),null
                    )
                    Column (
                        modifier = Modifier.weight(1F)
                    ){
                        Text("Location")
                        Text("St. no 8, KTM, Nepal")
                    }
                    Icon(
                        painterResource(R.drawable.baseline_arrow_back_ios_24),null
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

        }
    }
}

@Composable
@Preview
fun HomePreview() {
    HomeScreen()
}