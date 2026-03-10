package com.example.examen.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Product(
    val id: Int,
    val name: String,
    val price: Double
)

class ProductViewModel : ViewModel() {

    var products by mutableStateOf(listOf(Product(id = 1, name = "anillo", price = 1299.99),
        Product(id = 2, name = "collar", price = 89.50))
    )
        private set

    fun addProduct(name: String, price: Double) {
        val nextId = (products.maxOfOrNull { it.id } ?: 0) + 1
        val newProduct = Product(id = nextId, name = name, price = price)

        products = products + newProduct
    }

    fun deleteProduct(id: Int) {
        products = products.filter { it.id != id }
    }

    fun getProductById(id: Int): Product? {
        return products.find { it.id == id }
    }

}


