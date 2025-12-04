package com.example.demoapp.model

// Model uses Data Class

data class UserModel(
    val userId : String = "",
    val firstName : String = "",
    val lastName : String = "",
    val email : String = "",
    val gender : String = "",
    val dob : String = "",
)
