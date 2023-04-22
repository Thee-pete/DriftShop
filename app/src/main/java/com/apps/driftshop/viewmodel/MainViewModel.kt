package com.apps.driftshop.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.apps.driftshop.api.ApiInterface
import com.apps.driftshop.api.ProductRepository
import com.apps.driftshop.model.Product
import com.apps.driftshop.room.ProductDatabase
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val productRepository = ProductRepository(ProductDatabase.getDatabase(application))
    val products = productRepository.products
    private val _products = MutableLiveData<List<Product>>()
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>get() = _isNetworkErrorShown

    init{
        refreshDataFromRepository()
    }
    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try{
                productRepository.refreshProducts()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            }catch(networkError: IOException){
                if(products.value.isNullOrEmpty())
                    _isNetworkErrorShown.value = true

            }
        }
    }
    fun onNetworkErrorShow(){
        _isNetworkErrorShown.value = false
    }

}