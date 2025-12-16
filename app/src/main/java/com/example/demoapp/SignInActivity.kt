package com.example.demoapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demoapp.repository.UserRepo
import com.example.demoapp.repository.UserRepoImpl
import com.example.demoapp.ui.theme.Black
import com.example.demoapp.ui.theme.Blue
import com.example.demoapp.ui.theme.PurpleGrey40
import com.example.demoapp.ui.theme.PurpleGrey80
import com.example.demoapp.ui.theme.White
import com.example.demoapp.viewmodel.UserViewModel

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignInBody()
        }
    }
}

@Composable
fun SignInBody(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }
    var rememberDetail by remember { mutableStateOf(false) }

    val userViewModel= remember { UserViewModel(UserRepoImpl()) }

    val context = LocalContext.current
    val activity = context as Activity

//    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)

    Scaffold {
        values ->
        Column (
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(100.dp))

            Text(
                "Sign In",
                style = TextStyle(
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                "This is lorem epsum ecommerce application where you can buy multiple products at cheap price",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Black.copy(0.5f) // to adjust the opacity or modify the current color
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
            )

            Row {
                SocialMediaCard(
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f),
                    R.drawable.facebook_logo,
                    "Facebook"
                )

                Spacer(Modifier.width(10.dp))

                SocialMediaCard(
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f),
                    R.drawable.google_logo,
                    "Google"
                )
            }

            Row(
                modifier = Modifier.padding(vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))

                Text("OR", modifier = Modifier.padding(horizontal = 10.dp))

                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            OutlinedTextField(
                value = email,
                onValueChange = { data ->
                    email = data
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                placeholder = {Text("abc@gmail.com")},
                shape = RoundedCornerShape(15.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = Blue.copy(0.01f),
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Black.copy(0.25f)
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { data -> password = data},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                placeholder = {
                    Text("•••••••••••")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        visibility = !visibility
                    }) {
                        Icon(
                            painter = if (visibility)
                            painterResource(R.drawable.visibility_off)
                            else
                            painterResource(R.drawable.visible),
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if(visibility) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(15.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = Blue.copy(0.01f),
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Black.copy(0.25f)
                )
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ){
                    Checkbox(checked = rememberDetail, onCheckedChange = { data -> rememberDetail = data })
                    Text("Remember Me")
                }
                Text("Forget Password?", color = Black.copy(0.25f))
            }

            Button(onClick = {
                if(email != "" && password != ""){
                    userViewModel.login(email,password){
                        success,message->
                        if(success){
                            val intent = Intent(context, DashboardActivity::class.java)
                            Toast.makeText(context,message,Toast.LENGTH_SHORT)
                            context.startActivity(intent)
                            activity.finish()
                        } else{
                            Toast.makeText(context,message,Toast.LENGTH_SHORT)
                        }
                    }
                }else{
                    Toast.makeText(context,"Email or Password not entered",Toast.LENGTH_SHORT)
                }
//                val localEmail:String? = sharedPreferences.getString("email","")
//                val localPassword:String? = sharedPreferences.getString("password","")
//                if(email == localEmail){
//                    val intent = Intent(context, DashboardActivity::class.java)
//                    // Passing value in Navigation
////                    intent.putExtra("email",email)
////                    intent.putExtra("password",password)
//                    Toast.makeText(context,"Logged in Successfully!",Toast.LENGTH_SHORT)
//                    context.startActivity(intent)
//                    activity.finish()
//                } else {
//                    Toast.makeText(context,"Incorrect Credentials",Toast.LENGTH_SHORT)
//                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 15.dp)) {
                Text("Log In")
            }

            Text(buildAnnotatedString {
                append("Don't have an account? ")

                withStyle(SpanStyle(color = Blue)){
                    append("Sign Up")
                }
            },
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clickable {
                        val intent = Intent(context, RegistrationActivity::class.java)
                        context.startActivity(intent)
//                        activity.finish()
                    }
            )
        }
    }
}

@Composable
fun SocialMediaCard(modifier: Modifier,logo:Int,name:String){
    Card (
        modifier = modifier
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(logo),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(Modifier.width(15.dp))

            Text(name)
        }
    }
}

@Preview
@Composable
fun PreviewSignIn(){
    SignInBody()
}