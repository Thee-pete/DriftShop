package com.apps.driftshop.api

import com.apps.driftshop.model.Product
import com.apps.driftshop.room.ProductDao

class ProductRepository(private val apiInterface: ApiInterface,private val productDao: ProductDao ){
    suspend fun saveProducts(){
        val products = apiInterface.getProducts()
        productDao.insert(products)
    }

    suspend fun getProductsFromDb(): List<Product>{
        return productDao.getProducts()
    }
}