package com.example.ch13.application.domain

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

    fun deposit(amount: BigDecimal) {
        if (amount < BigDecimal.ZERO) {
            throw IllegalArgumentException("deposit amount was negative")
        }
        this.amount = this.amount.add(amount)
    }

    fun withdraw(amount: BigDecimal) {
        if (amount < BigDecimal.ZERO) {
            throw IllegalArgumentException("withdraw amount was negative")
        }
        this.amount = this.amount.subtract(amount)
    }
}