package pl.edu.uj.shop.Models

data class Order(
    val id: Int,
    val userId: Int,
    val productId: Int,
    val quantity: Int
)
