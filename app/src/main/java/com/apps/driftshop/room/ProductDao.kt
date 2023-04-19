package com.apps.driftshop.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.driftshop.model.Product

interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(product: List<Product>)

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<Product>
}