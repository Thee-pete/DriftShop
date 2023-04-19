package com.apps.driftshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.driftshop.api.ProductRepository

class MainViewModelFactory (private val productRepository: ProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(productRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel Not Found")
    }
}