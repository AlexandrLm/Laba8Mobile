package com.example.laba8mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter

    var products = mutableListOf(
        Product(0,"Product 1", "Category 1", "Description 1", R.drawable.ic_launcher_foreground),
        Product(1,"Product 2", "Category 2", "Description 2", R.drawable.ic_launcher_foreground),
        Product(2,"Product 3", "Category 3", "Description 3", R.drawable.image1)
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        products.add(Product(products.size,"Product 4", "Category 4", "Description 4", R.drawable.image1))

        productAdapter = ProductAdapter(this, products, ::onProductClick)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onProductClick(product: Product) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product", product)
        startActivityForResult(intent, 1)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val updatedProduct = data?.getParcelableExtra<Product>("updated_product")

            if (updatedProduct != null) {
                val index = products.indexOfFirst { it.id == updatedProduct.id }
                products[index] = updatedProduct

                productAdapter.notifyItemChanged(index)
            }

            }
        }
    }

