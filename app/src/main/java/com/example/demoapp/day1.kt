package com.example.demoapp

fun main(){
//    println("Hello")
//    println("World")

//    // mutable
//    var name: String = "Bivek"
//    name = "Ramesh"
//    // immutable
//    val age: Int = 15
//
//    print("My name is $name and age is $age") //interpolation - using $
//    print("My name is ${name.uppercase()} and age is $age") //in {} we can access datatype properties

//    //Array
//    val age = arrayOf(10,20,30) // static array- fixed array
//    age[2] = 50
//    println(age[2])
//
//    val age1 = ArrayList<Int>()
//    val age2 = arrayListOf<Int>(10,20,30)
//
//    age1.add(5)
//    age2.add(10)

//  For Loop
//    for(i in 0 .. 10 step 2){
//        print(i)
//    }
//    for(i in 0 until 10 step 2){
//        print(i)
//    }
//    for(i in 10 downTo 0){
//        print(i)
//    }
    val result = sum(5,8)
    print(result)
    loginInfo("bivekthapa","123456")

}

// Function
fun sum(a:Int,b:Int) : Int{
    return a+b
}
fun loginInfo(username:String,password:String):Unit{
    print("Login info function called here's username $username and password is $password.")
}