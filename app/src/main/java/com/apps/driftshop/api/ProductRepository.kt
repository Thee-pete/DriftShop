package com.apps.driftshop.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apps.driftshop.model.Product
import com.apps.driftshop.room.ProductDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductRepository(private val apiInterface: ApiInterface,private val productDao: ProductDao){
    fun getAllProducts(): LiveData<List<Product>> {
        val products = MutableLiveData<List<Product>?>()

        apiInterface.getProducts().enqueue(object: Callback<List<Product>>{

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val fetchedProducts = response.body()
                products.value = fetchedProducts
                fetchedProducts?.let{saveProducts(it)}
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return productDao.getProducts()

    }
    private fun saveProducts(products: List<Product>){
        for (product in products) {
            productDao.insert(product)
        }
    }



}