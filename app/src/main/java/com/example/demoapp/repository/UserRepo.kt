package com.example.demoapp.repository

import com.example.demoapp.model.UserModel

// Repository uses Interface

interface UserRepo {

    fun login(email:String,password:String,callback:(Boolean, String) -> Unit)

    fun register(email: String,password: String,callback: (Boolean, String, String) -> Unit)

    fun addUserToDatabase(userId: String, model: UserModel,callback: (Boolean, String) -> Unit)

    fun forgetPassword(email: String,callback: (Boolean, String) -> Unit)

    fun getUserById(userId: String, callback: (Boolean, String, UserModel?) -> Unit)

    fun getAllUser(callback: (Boolean, String, List<UserModel>?) -> Unit)

    fun editProfile(userId: String, model: UserModel, callback: (Boolean, String) -> Unit)

    fun deleteUser(userId: String, callback: (Boolean, String) -> Unit)

}