package com.example.ch10

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

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

/**
 * PaymentControllerV2 doesn't treat the exception case, instead it delegates such
 * concerns to @RestControllerAdvice.
 */
@RestController
class PaymentControllerV2(
    private val paymentService: PaymentService
) {

    @PostMapping("/v2/payment")
    fun makePaymentV2(): ResponseEntity<PaymentDetails> {
        val paymentDetails = this.paymentService.processPayment()
        return ResponseEntity.accepted().body(paymentDetails)
    }
}

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(NotEnoughMoneyException::class)
    fun exceptionNotEnoughMoneyHandler(): ResponseEntity<ErrorDetails> {
        val errDetails = ErrorDetails("Not enough money to make the payment.")
        return ResponseEntity.badRequest().body(errDetails)
    }
}

@RestController
class PaymentEchoController {

    @PostMapping("/v1/payment-echo")
    fun echoPayment(
        @RequestBody paymentDetails: PaymentDetails
    ): ResponseEntity<PaymentDetails> {
        return ResponseEntity.ok(paymentDetails)
    }
}
