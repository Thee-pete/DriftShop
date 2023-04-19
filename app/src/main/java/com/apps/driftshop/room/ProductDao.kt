package com.apps.driftshop.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.driftshop.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)
}