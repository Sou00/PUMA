package com.example.Models

data class Order(
    val id: Int,
    val userId: Int,
    val productId: Int,
    val quantity: Int
)
