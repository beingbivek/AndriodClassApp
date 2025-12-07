package com.example.demoapp.repository

import com.example.demoapp.model.ProductModel

interface ProductRepo {
    fun addProduct(model: ProductModel, callback: (Boolean, String)-> Unit)

    fun editProduct(model: ProductModel, callback: (Boolean, String) -> Unit)

    fun deleteProduct(productId : String, callback: (Boolean, String) -> Unit)

    fun getProductById(productId: String, callback: (Boolean, String, ProductModel?) -> Unit)

    fun getAllProducts(callback: (Boolean, String, List<ProductModel>?) -> Unit)
}