package com.example.ch11app

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "payments", url = "\${name.service.url}")
interface PaymentsProxy {

    @PostMapping("/payment")
    fun createPayment(
        @RequestHeader requestId: String,
        @RequestBody payment: Payment
    ): Payment
}