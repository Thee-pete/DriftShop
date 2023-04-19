package com.apps.driftshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.driftshop.adapters.ProductAdapter
import com.apps.driftshop.api.ApiInterface
import com.apps.driftshop.api.ApiInterface.Companion.apiInstance
import com.apps.driftshop.api.ProductRepository
import com.apps.driftshop.room.ProductDatabase
import com.apps.driftshop.viewmodel.MainViewModel
import com.apps.driftshop.viewmodel.MainViewModelFactory
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter:ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView:RecyclerView = findViewById(R.id.product_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter()
        recyclerView.adapter = productAdapter

        val productDao = ProductDatabase.getDatabase(application).productDao()
        val apiInterface = ApiInterface.getInstance()
        val productRepository = ProductRepository(apiInterface,productDao)
        val mainViewModel = ViewModelProvider(this, MainViewModelFactory(productRepository)).get(MainViewModel::class.java)

        mainViewModel.getProducts().observe(this, Observer { products->
            productAdapter.setProducts(products)
        })

    }
}