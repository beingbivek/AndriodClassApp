package com.example.demoapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import androidx.compose.ui.unit.toSize
import com.example.demoapp.model.UserModel
import com.example.demoapp.repository.UserRepoImpl
import com.example.demoapp.ui.theme.Blue
import com.example.demoapp.ui.theme.PurpleGrey80
import com.example.demoapp.ui.theme.White
import com.example.demoapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistrationBody()
        }
    }
}

@Composable
fun RegistrationBody(userViewModel: UserViewModel? = null){

    // Only create UserViewModel when NOT in Preview
    val uVM = userViewModel ?: remember {
        UserViewModel(UserRepoImpl())
    }


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }
    var terms by remember { mutableStateOf(false) }

    // For Calender
    var selectedDate by remember { mutableStateOf("") }

    val context = LocalContext.current
    val activity = context as Activity

    val calender = Calendar.getInstance()

    val year = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH)
    val day = calender.get(Calendar.DAY_OF_MONTH)

    val datepicker = DatePickerDialog(
        context, { _, year, month, day ->
            selectedDate = "$day/${month + 1}/$year"
        }, year, month, day
    )

    // Shared Preferences
    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    // For Drop Down

    var expanded by remember {mutableStateOf(false)}
    var selectedOptionText by remember { mutableStateOf("Select Option") }
    val options = listOf("Male","Female","Others")
    var textFieldSize by remember {mutableStateOf(Size.Zero)}

    // For SnackBar
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Alert Dialog
    var showDialog by remember { mutableStateOf(false) }

    Scaffold (
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(100.dp))

            Text(
                "Sign Up",
                style = TextStyle(
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                ),
                modifier = Modifier.fillMaxWidth()
            )

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

            OutlinedTextField(
                value = firstName,
                onValueChange = { data ->
                    firstName = data
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = Blue.copy(0.01f),
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Black.copy(0.25f)
                )
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { data ->
                    lastName = data
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = Blue.copy(0.01f),
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Black.copy(0.25f)
                )
            )

            // Date picker code

            OutlinedTextField(
                enabled = false,
                value = selectedDate,
                onValueChange = {
                    selectedDate = it
                                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp).clickable{
                        datepicker.show()
                    },
                placeholder = {Text("dd/mm/yyyy")},
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    disabledContainerColor = PurpleGrey80,
                    focusedContainerColor = White,
                    unfocusedContainerColor = Blue.copy(0.01f),
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Black.copy(0.25f)
                )
            )

            // Drop down code

            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                OutlinedTextField(
                    enabled = false,
                    value = selectedOptionText,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned{textFieldSize=it.size.toSize()}
                        .clickable{
                            expanded = true
                        },
                    placeholder = {Text("Select Option")},
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        disabledIndicatorColor = Color.Transparent,
                        disabledContainerColor = PurpleGrey80,
                        focusedContainerColor = White,
                        unfocusedContainerColor = Blue.copy(0.01f),
                        focusedIndicatorColor = Blue,
                        unfocusedIndicatorColor = Color.Black.copy(0.25f)
                    ),
                    trailingIcon = {
                        if (!expanded) Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        ) else Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null
                        )
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false},
                    modifier = Modifier
                        .width(with(LocalDensity.current){
                            textFieldSize.width.toDp()
                        })
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(option)
                            },
                            onClick = {
                                selectedOptionText = option
                                expanded = false
                                // Toast Code
                                Toast.makeText(context, "You Selected $option", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = terms,
                    onCheckedChange = {
                        terms=it
                        // Snackbar Code
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("You have entered date $selectedDate")
                        }
                                      },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Blue,
                        checkmarkColor = White
                    )
                )
                Text("I agree to Terms & Conditions.")
            }

            Button(
                onClick = {
                    if (!terms) {
                        Toast.makeText(
                            context,
                            "please agree to terms &  conditions",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        uVM.register(email,password){
                                success,message,userId->
                            if(success){
                                var model = UserModel(
                                    userId = userId,
                                    firstName = firstName,
                                    lastName = lastName,
                                    gender = selectedOptionText,
                                    email = email,
                                    dob = selectedDate
                                )
                                uVM.addUserToDatabase(
                                    userId,model
                                ){
                                        success,message->
                                    if(success){
                                        Toast.makeText(
                                            context,
                                            message,
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        activity.finish()
                                    }else{
                                        Toast.makeText(
                                            context,
                                            message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }else{
                                Toast.makeText(
                                    context,
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

//                        editor.putString("email",email)
//                        editor.putString("password",password)
//                        editor.putString("date",selectedDate)
//
//                        editor.apply()
//                        Toast.makeText(
//                            context,
//                            "Success",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        activity.finish()

                    }
                },
//                onClick = {
//                // Alert dialog trigger
//                showDialog = true
//
//                // check email is already same or not
//                val localEmail:String? = sharedPreferences.getString("email","")
//                if (localEmail==email){
//                    Toast.makeText(context,"This email has already been registered!",Toast.LENGTH_SHORT)
//                } else {
//                    // Putting values in shared preferences(local) if email is new
//                    editor.putString("email",email)
//                    editor.putString("password",password)
//                    editor.putString("date",selectedDate)
//                    editor.putString("gender",selectedOptionText)
//                    Toast.makeText(context,"Successfully registered!",Toast.LENGTH_SHORT)
//                    activity.finish()
//                }
//            },
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp).height(50.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 15.dp)) {
                Text("Sign Up")
            }

            // Alert Dialog Code

            if(showDialog){
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    confirmButton = {
                        Button(onClick = {showDialog= false}) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {showDialog = false}) {
                            Text("Cancel")
                        }
                    },
                    text = {Text("Are you sure you want to login?")},
                    title = {Text("Warning!")}
                )
            }

            Text(buildAnnotatedString {
                append("Already have account? ")

                withStyle(SpanStyle(color = Blue)){
                    append("Sign In")
                }
            },
                modifier = Modifier.padding(vertical = 10.dp))
        }
    }
}


@Preview
@Composable
fun PreviewRegistration(){
    RegistrationBody()
}