package com.apps.driftshop.api

import com.apps.driftshop.room.ProductDao


class ProductRepository(private val apiInterface: ApiInterface,private val productDao: ProductDao ){


}