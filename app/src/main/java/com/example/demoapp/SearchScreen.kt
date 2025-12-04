package com.example.demoapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demoapp.ui.theme.BgColor
import com.example.demoapp.ui.theme.Blue
import com.example.demoapp.ui.theme.White

@Composable
fun SearchScreen() {
    var query by remember { mutableStateOf("Headphones") }

    val products = listOf(
        ProductItem("Crystal Headphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Wireless Headphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Sonic Headphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Vintage Headphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Multi Headphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Wireless Earphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Samsung Earphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Fantech Earphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Misio Earphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Boat Earphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Sony Headphones", "PKR 800", "PKR 1300", R.drawable.headphones),
        ProductItem("Apple Headphones", "PKR 800", "PKR 1300", R.drawable.headphones)
    )

    Scaffold { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(BgColor)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.size(8.dp))

                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier
                        .weight(1f),
                    placeholder = { Text("Search") },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_search_24),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_mic_none_24),
                            contentDescription = null
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.size(8.dp))

                Icon(
                    painter = painterResource(R.drawable.baseline_filter_alt_24),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Showing: 60 results for headphones",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Sort By",
                    fontSize = 12.sp,
                    color = Blue,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(products) { item ->
                    SearchProductCard(item)
                }
            }
        }
    }
}

@Composable
fun SearchProductCard(item: ProductItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = item.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.price,
                fontSize = 12.sp,
                color = Blue,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.oldPrice,
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    SearchScreen()
}
