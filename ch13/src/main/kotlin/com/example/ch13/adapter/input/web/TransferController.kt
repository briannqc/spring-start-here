package com.example.ch13.adapter.input.web

import com.example.ch13.port.input.TransferUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

@RestController
class TransferController(
    private val transferService: TransferUseCase
) {

    data class TransferRequest(
        val senderId: UUID,
        val receiverId: UUID,
        val amount: BigDecimal,
    )

    @PostMapping("/transfer")
    fun transfer(@RequestBody req: TransferRequest) {
        this.transferService.transferMoney(req.senderId, req.receiverId, req.amount)
    }
}

