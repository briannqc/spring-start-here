package com.example.ch12

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PurchaseController(
    private val purchaseRepository: PurchaseRepository
) {

    @PostMapping("/purchases")
    fun storePurchase(
        @RequestBody purchase: Purchase
    ) {
        this.purchaseRepository.storePurchase(purchase)
    }

    @GetMapping("/purchases")
    fun findPurchases(): List<Purchase> {
        return this.purchaseRepository.findAllPurchases()
    }
}