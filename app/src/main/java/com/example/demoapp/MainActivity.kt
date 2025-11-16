package com.example.demoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {      // MainActivity extends(:) ComponentActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainBody()
        }
    }
}
// 3 layout in Jetpack compose: row, column and box
// dp is used for layout size
// sp is used for text size
@Composable
fun MainBody(){
    Scaffold { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row {
                Text("This is a DemoApp")
                Text("Great AI"
                    , modifier = Modifier.padding(horizontal = 10.dp)
//                    , modifier = Modifier.padding(50.dp)
                )
            }
            Text("Hello"
//                , modifier = Modifier.fillMaxWidth()
            )
            Text("Android", style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            ), modifier = Modifier.background(color = Color.Red))
            Text("Bivek")
        }
    }
}

@Preview
@Composable
fun MainPreview(){
    MainBody()
}