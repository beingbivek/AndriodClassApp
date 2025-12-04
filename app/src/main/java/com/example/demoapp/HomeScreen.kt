package com.example.demoapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demoapp.ui.theme.BgColor
import com.example.demoapp.ui.theme.Blue
import com.example.demoapp.ui.theme.CardBGColor
import com.example.demoapp.ui.theme.White

data class CategoryItem(val name: String, val iconRes: Int)
data class ProductItem(val name: String, val price: String, val oldPrice: String, val imageRes: Int)

@Composable
fun HomeScreen() {
    var search by remember { mutableStateOf("") }

    val categories = listOf(
        CategoryItem("Beauty", R.drawable.wrapped_1),
        CategoryItem("Home & Decor", R.drawable.artist_2),
        CategoryItem("Fashion", R.drawable.artist_1),
        CategoryItem("Appliances", R.drawable.wrapped_1),
        CategoryItem("Party items", R.drawable.artist_1),
        CategoryItem("Beauty+", R.drawable.google_logo)
    )

    val flashSale = listOf(
        ProductItem("Sonic Headphones", "PKR 800", "PKR 1500", R.drawable.artist_1),
        ProductItem("Mini Clock", "PKR 800", "PKR 1300", R.drawable.artist_2),
        ProductItem("Wireless Earpods", "PKR 800", "PKR 1500", R.drawable.artist_1)
    )

    val recommended = listOf(
        ProductItem("Perfume", "PKR 900", "PKR 1400", R.drawable.artist_2),
        ProductItem("Makeup Kit", "PKR 1200", "PKR 1800", R.drawable.artist_1),
        ProductItem("Gift Box", "PKR 700", "PKR 1100", R.drawable.artist_2)
    )

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
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search here") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedIndicatorColor = White,
                    unfocusedIndicatorColor = White
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_search_24),
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(CardBGColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_location_pin_24),
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text("Location", fontSize = 13.sp, color = Color.Gray)
                        Text("St. no 8, KTM, Nepal", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Icon(
                        painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Image(
                painter = painterResource(R.drawable.sale_banner),null
            )

            Spacer(modifier = Modifier.height(18.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(categories) { cat ->
                    CategoryCard(cat)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Flash Sale", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Only few items left, ending soon!",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Text("20 items left", fontSize = 12.sp, color = Blue)
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(flashSale) { product ->
                    ProductCard(product)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Recommended for you", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Buy them before it’s too late!",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Text("See all", fontSize = 12.sp, color = Blue)
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(recommended) { product ->
                    ProductCard(product)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CategoryCard(item: CategoryItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(White),
            modifier = Modifier
                .size(70.dp)
        ) {
            Image(
                painter = painterResource(item.iconRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(item.name, fontSize = 12.sp)
    }
}

@Composable
fun ProductCard(item: ProductItem) {
    Card(
        modifier = Modifier
            .width(140.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(White)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(item.name, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(item.price, fontSize = 12.sp, color = Blue, fontWeight = FontWeight.Bold)
            Text(
                item.oldPrice,
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}
