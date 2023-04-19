package com.apps.driftshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product (
    @PrimaryKey val id : String,
    val name : String,
    val imageUrl : String,
    val price:Int
    )
