package com.apps.driftshop.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apps.driftshop.model.Product

@Database(entities = [Product::class], version=1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
    companion object{
        @Volatile
        private var INSTANCE : ProductDatabase? =null
        fun getDatabase(context: Context): ProductDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, ProductDatabase::class.java,"PRODUCT_DB")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }

}