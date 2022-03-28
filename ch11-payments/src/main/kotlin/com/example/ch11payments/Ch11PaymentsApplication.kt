package com.example.ch11payments

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.util.*

@SpringBootApplication
class Ch11PaymentsApplication

fun main(args: Array<String>) {
    runApplication<Ch11PaymentsApplication>(*args)
}

data class Payment(
    val id: String?,
    val amount: Double,
)

@RestController
class PaymentController {

    @PostMapping("/payment")
    fun createPayment(
        @RequestHeader requestId: String,
        @RequestBody payment: Payment,
    ): ResponseEntity<Payment> {
        val p = Payment(UUID.randomUUID().toString(), payment.amount)

        return ResponseEntity
            .status(HttpStatus.OK)
            .header("requestId", requestId)
            .body(p)
    }
}