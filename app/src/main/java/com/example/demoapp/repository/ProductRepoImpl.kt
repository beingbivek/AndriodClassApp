package com.example.demoapp.repository

import com.example.demoapp.model.ProductModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductRepoImpl: ProductRepo {

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()

    val ref : DatabaseReference = database.getReference("Product")

    override fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        model.productId = ref.push().key.toString()
        ref.child(model.productId).setValue(model).addOnCompleteListener {
            if (it.isSuccessful){
                callback(true,"Product added successfully!")
            }else{
                callback(false,"${it.exception?.message}")
            }
        }
    }

    override fun editProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(model.productId).setValue(model).addOnCompleteListener {
            if (it.isSuccessful){
                callback(true,"Product edited successfully!")
            }else{
                callback(false,"${it.exception?.message}")
            }
        }
    }

    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId).removeValue().addOnCompleteListener {
            if (it.isSuccessful){
                callback(true,"Product deleted successfully!")
            }else{
                callback(false,"${it.exception?.message}")
            }
        }
    }

    override fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    ) {
        ref.child(productId).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val product = snapshot.getValue(ProductModel::class.java)
                        if(product!=null){
                            callback(true,"Product fetched",product)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false,"${error.message}",null)
                }
            }
        )
    }

    override fun getAllProducts(callback: (Boolean, String, List<ProductModel>?) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var allProducts = mutableListOf<ProductModel>()
                    for(data in snapshot.children){
                        val user = snapshot.getValue(ProductModel::class.java)
                        if(user!=null){
                            allProducts.add(user)
                        }
                    }
                    callback(true,"All products fetched",allProducts)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false,"${error.message}",null)
            }

        })
    }
}