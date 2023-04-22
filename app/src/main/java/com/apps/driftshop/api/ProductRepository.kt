package com.apps.driftshop.api


import androidx.lifecycle.LiveData
import com.apps.driftshop.model.Product
import com.apps.driftshop.room.ProductDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ProductRepository(private val productDatabase: ProductDatabase){
    val products: LiveData<List<Product>> = productDatabase.productDao().getProducts()

    suspend fun refreshProducts() {
        withContext(Dispatchers.IO) {
            val productList = ApiInterface.getInstance().getProducts()
            productDatabase.productDao().insert(productList)
        }
    }




}