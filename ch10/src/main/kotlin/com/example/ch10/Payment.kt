package com.example.ch10

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

data class PaymentDetails(
    val amount: Long
)

data class ErrorDetails(
    val message: String
)

class NotEnoughMoneyException : RuntimeException()

@Service
class PaymentService {

    fun processPayment(): PaymentDetails {
        throw NotEnoughMoneyException()
    }
}

@RestController
class PaymentController(
    private val paymentService: PaymentService
) {

    /**
     * makePayment handles exceptions in controller level.
     *
     * This works fine for small application, though it's not really extendable as it introduces
     * more duplications when the app grows.
     */
    @PostMapping("/v1/payment")
    fun makePayment(): ResponseEntity<Any> {
        return try {
            val paymentDetails = this.paymentService.processPayment()
            ResponseEntity.accepted().body(paymentDetails)
        } catch (ex: NotEnoughMoneyException) {
            val errDetails = ErrorDetails("Not enough money to make the payment")
            ResponseEntity.badRequest().body(errDetails)
        }
    }
}