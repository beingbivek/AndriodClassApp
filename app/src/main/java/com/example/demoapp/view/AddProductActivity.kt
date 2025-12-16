package com.example.demoapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoapp.DashboardActivity
import com.example.demoapp.R
import com.example.demoapp.model.ProductModel
import com.example.demoapp.repository.ProductRepoImpl
import com.example.demoapp.ui.theme.Blue
import com.example.demoapp.ui.theme.White
import com.example.demoapp.viewmodel.ProductViewModel

class AddProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddProductBody()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductBody(){
    val context = LocalContext.current
    val activity = context as Activity
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    val productViewModel = remember { ProductViewModel(ProductRepoImpl()) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White,
                    navigationIconContentColor = White,
                    actionIconContentColor = White
                ),
                title = { Text("Add Product") },
                navigationIcon = {
                    IconButton(onClick = {

                        activity.finish()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = null
                        )

                    }
                }
            )
        },
    ) {
        pad->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(pad),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                OutlinedTextField(
                    value = productName,
                    onValueChange = {
                        productName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    placeholder = {Text("Name")},
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = Blue.copy(0.01f),
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Black.copy(0.25f)
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(
                    value = productPrice,
                    onValueChange = { productPrice = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    placeholder = {Text("0.0")},
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = Blue.copy(0.01f),
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Black.copy(0.25f)
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = productDescription,
                    onValueChange = {
                        productDescription = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    placeholder = {Text("Product Description")},
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = Blue.copy(0.01f),
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Black.copy(0.25f)
                    )
                )
                Spacer(Modifier.height(15.dp))
                Button(onClick = {
                    if(productName != "" && productDescription != "" && productPrice != ""){

                        var model = ProductModel(
                            name = productName,
                            price = productPrice.toDoubleOrNull() ?: 0.0,
                            description = productDescription
                        )
                        productViewModel.addProduct(model){
                                success,message->
                            if(success){
                                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                                activity.finish()
                            } else{
                                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(context,"Enter All Details",Toast.LENGTH_SHORT).show()
                    }
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = buttonElevation(defaultElevation = 15.dp)
                ) {
                    Text("Add product")
                }
            }
        }
    }
}
