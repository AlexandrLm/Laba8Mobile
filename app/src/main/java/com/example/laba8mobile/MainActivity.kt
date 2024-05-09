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
        Product(0,"Product 1", "Category 1", "Description 1", R.drawable.image1),
        Product(1,"Product 2", "Category 2", "Description 2", R.drawable.image2),
        Product(2,"Product 3", "Category 3", "Description 3", R.drawable.image3)
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        products.add(Product(products.size,"Product 4", "Category 4", "Description 4", R.drawable.image4))
        products.add(Product(products.size,"Product 5", "Category 5", "Description 5", R.drawable.image5))
        products.add(Product(products.size,"Product 6", "Category 6", "Description 6", R.drawable.image6))
        products.add(Product(products.size,"Product 7", "Category 7", "Description 7", R.drawable.image7))
        products.add(Product(products.size,"Product 8", "Category 8", "Description 8", R.drawable.image8))
        products.add(Product(products.size,"Product 9", "Category 9", "Description 9", R.drawable.image9))
        products.add(Product(products.size,"Product 10", "Category 10", "Description 10", R.drawable.image10))
        products.add(Product(products.size,"Product 11", "Category 11", "Description 11", R.drawable.image11))

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

