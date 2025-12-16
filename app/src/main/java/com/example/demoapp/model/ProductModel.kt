package com.example.demoapp.model

data class ProductModel(
    var productId : String = "",
    var name : String = "",
    var price : Double = 0.0,
    var description : String = "",
){
    fun toMap() : Map<String, Any?>{
        return  mapOf(
            "productId" to productId,
            "name" to name,
            "price" to price,
            "description" to description,
        )
    }
}
