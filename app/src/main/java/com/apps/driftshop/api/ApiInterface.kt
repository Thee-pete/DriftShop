package com.apps.driftshop.api

import com.apps.driftshop.model.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    fun getProducts():Call<List<Product>>

    companion object{
        var apiInstance:ApiInterface? =null
        fun getInstance() : ApiInterface{
            if (apiInstance == null){
                val retrofit =Retrofit.Builder()
                    .baseUrl("https://api.drift.co.ke/api/test/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiInstance = retrofit.create((ApiInterface::class.java))

            }
            return apiInstance!!
        }
    }
}