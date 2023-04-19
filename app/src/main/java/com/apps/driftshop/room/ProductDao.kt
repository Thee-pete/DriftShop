package com.apps.driftshop.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.driftshop.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: List<Product>)

    @Query("SELECT * FROM products")
    fun getProducts(): List<Product>
}