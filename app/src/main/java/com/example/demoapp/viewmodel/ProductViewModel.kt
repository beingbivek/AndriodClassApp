package com.example.demoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoapp.model.ProductModel
import com.example.demoapp.model.UserModel
import com.example.demoapp.repository.ProductRepo
import kotlin.collections.emptyList

class ProductViewModel(
    private val repo: ProductRepo
) : ViewModel() {

    private val _product = MutableLiveData<ProductModel?>()
    val product : MutableLiveData<ProductModel?>
        get() = _product

    private val _allProducts = MutableLiveData<List<ProductModel>?>()
    val allProduct : MutableLiveData<List<ProductModel>?>
        get() = _allProducts

    private val _loading = MutableLiveData<Boolean>()
    val loading : MutableLiveData<Boolean>
        get() = _loading


    fun getProductById(productId: String){
        _loading.postValue(true)
        repo.getProductById(productId){
                success,msg,data->
            if(success){
                _product.postValue(data)
                _loading.postValue(false)
            }
            _loading.postValue(false)
        }
    }

    fun getAllProducts(){
        _loading.postValue(true)
        repo.getAllProducts {
                success,msg,data->
            if(success){
                _allProducts.postValue(data)
                _loading.postValue(false)
            }
            _loading.postValue(false)
        }
    }


    // CRUD operations
    fun addProduct(model: ProductModel, callback: (Boolean, String) -> Unit) =
        repo.addProduct(model, callback)

    fun editProduct(model: ProductModel, callback: (Boolean, String) -> Unit) =
        repo.editProduct(model, callback)

    fun deleteProduct(productId: String, callback: (Boolean, String) -> Unit) =
        repo.deleteProduct(productId, callback)
}
