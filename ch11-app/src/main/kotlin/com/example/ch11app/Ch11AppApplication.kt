package com.example.ch11app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@SpringBootApplication
@EnableFeignClients(basePackages = ["com.example.ch11app"])
class Ch11AppApplication

fun main(args: Array<String>) {
    runApplication<Ch11AppApplication>(*args)
}

data class Payment(
    val id: String?,
    val amount: Double,
)

@RestController
class PaymentController(
    private val paymentsProxy: PaymentsProxy
) {

    @PostMapping("payment")
    fun createPayment(
        @RequestBody payment: Payment
    ): Payment {
        val requestId = UUID.randomUUID().toString()
        return this.paymentsProxy.createPayment(requestId, payment)
    }
}
