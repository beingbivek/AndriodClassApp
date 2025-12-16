package com.example.demoapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.demoapp.ui.theme.DemoAppTheme
import com.example.demoapp.ui.theme.White
import com.example.demoapp.ui.theme.Blue
import com.example.demoapp.view.AddProductActivity

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardBody()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardBody(){
    val context = LocalContext.current
    val activity = context as Activity
    // Putting passed value to variable using navigation
//    val email = activity.intent.getStringExtra("email")
//    val password = activity.intent.getStringExtra("password")

    // Local Data
//    var sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)

//    val localEmail:String?= sharedPreferences.getString("email","")
//    var localPassword = sharedPreferences.getString("password","")
//    var localDate = sharedPreferences.getString("date","")
//    var localGender = sharedPreferences.getString("gender","")

    data class NavItem(val label:String,val icon:Int)

    var selectedItem by remember { mutableIntStateOf(0) }

    val navList = listOf(
        NavItem("Home", R.drawable.outline_home_24),
        NavItem("Search", R.drawable.baseline_search_24),
        NavItem("Notification", R.drawable.outline_notifications_24),
        NavItem("Profile", R.drawable.outline_person_24),
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, AddProductActivity::class.java)
                    activity.startActivity(intent)
                }
            ) {
                Icon(
                    Icons.Default.Add,null
                )
            }
        },
        topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Blue,
                titleContentColor = White,
                navigationIconContentColor = White,
                actionIconContentColor = White
            ),
            title = { Text("Ecommerce") },
            navigationIcon = {
                IconButton(onClick = {

                    activity.finish()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = null
                    )

                }
            },
            actions = {
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_more_horiz_24),
                        contentDescription = null
                    )
                }
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_more_horiz_24),
                        contentDescription = null
                    )
                }
            }
        )
    },
        bottomBar = {
            NavigationBar {
                navList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(item.icon), null
                            )
                        },
                        label = {Text(text = item.label)},
                        onClick = {
                            selectedItem = index
                        },
                        selected = selectedItem == index
                    )
                }
            }
        }
    ){
        padding ->
//        Column (
//            modifier = Modifier.fillMaxSize().padding(padding),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Email: $localEmail")
//            Text("Password: $localPassword")
//            Text("Date: $localDate")
//            Text("Gender: $localGender")
//        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when(selectedItem){
                0 -> HomeScreen()
                1 -> SearchScreen()
                2 -> NotificationScreen()
                3 -> ProfileScreen()
                else -> HomeScreen()
            }
        }
    }
}