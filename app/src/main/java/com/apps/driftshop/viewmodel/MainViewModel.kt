package com.apps.driftshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.driftshop.api.ApiInterface
import com.apps.driftshop.api.ProductRepository
import com.apps.driftshop.model.Product
import com.apps.driftshop.room.ProductDatabase

class MainViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val products = MutableLiveData<List<Product>>()
    init{
        fetchProducts()
    }
    private fun fetchProducts(){
        products.postValue(productRepository.getAllProducts().value)
    }
    fun getProducts(): LiveData<List<Product>>{
        return products
    }
}