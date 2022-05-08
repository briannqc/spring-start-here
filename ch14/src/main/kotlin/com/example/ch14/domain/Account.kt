package com.example.ch14.domain

import java.math.BigDecimal
import java.util.UUID

class Account(
    val uuid: UUID,
    val name: String,
    private var amount: BigDecimal,
) {

    fun getAmount(): BigDecimal {
        return this.amount
    }

    fun withdraw(amount: BigDecimal) {
        if (amount < BigDecimal.ZERO) {
            throw IllegalArgumentException("withdraw a negative amount: $amount")
        }
        this.amount = this.amount.subtract(amount)
    }

    fun deposit(amount: BigDecimal) {
        if (amount < BigDecimal.ZERO) {
            throw IllegalArgumentException("deposit a negative amount: $amount")
        }
        this.amount = this.amount.add(amount)
    }
}