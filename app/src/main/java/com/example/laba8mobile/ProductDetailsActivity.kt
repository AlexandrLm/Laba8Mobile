package com.example.laba8mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        product = intent.getParcelableExtra("product")!!

        val imageView = findViewById<ImageView>(R.id.imageView)
        val nameEditText = findViewById<EditText>(R.id.editTextName)
        val categoryEditText = findViewById<EditText>(R.id.editTextCategory)
        val descriptionEditText = findViewById<EditText>(R.id.editTextDescription)
        val saveButton = findViewById<Button>(R.id.buttonSave)

        imageView.setImageResource(product.imageResourceId)
        nameEditText.setText(product.name)
        categoryEditText.setText(product.category)
        descriptionEditText.setText(product.description)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val category = categoryEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            if (name.isEmpty()) {
                nameEditText.error = "Name cannot be empty"
                return@setOnClickListener
            }

            if (category.isEmpty()) {
                categoryEditText.error = "Category cannot be empty"
                return@setOnClickListener
            }

            val updatedProduct = Product(product.id, name, category, description, product.imageResourceId)
            val intent = Intent().apply {
                putExtra("updated_product", updatedProduct)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}