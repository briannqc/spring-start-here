package com.example.ch12

import java.math.BigDecimal

data class Purchase(
    val id: Int,
    val product: String,
    val price: BigDecimal,
)