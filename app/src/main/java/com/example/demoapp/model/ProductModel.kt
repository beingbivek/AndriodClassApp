package com.example.demoapp.model

data class ProductModel(
    var productId : String = "",
    val productName : String = "",
    val quantity : String = "",
    val rate : String = "",
    val imageURL : String = "",
){
    fun toMap() : Map<String, Any?>{
        return  mapOf(
            "productId" to productId,
            "productName" to productName,
            "quantity" to quantity,
            "rate" to rate,
            "imageURL" to imageURL,
        )
    }
}
