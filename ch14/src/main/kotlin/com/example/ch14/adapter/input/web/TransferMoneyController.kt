package com.example.ch14.adapter.input.web

import com.example.ch14.application.port.input.TransferMoneyUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

@RestController
class TransferMoneyController(
    private val transferMoneyUseCase: TransferMoneyUseCase
) {

    data class TransferMoneyRequest(
        val senderUUID: UUID,
        val receiverUUID: UUID,
        val amount: BigDecimal,
    )

    @PostMapping("/transfer")
    fun transfer(@RequestBody req: TransferMoneyRequest) {
        this.transferMoneyUseCase.transferMoney(req.senderUUID, req.receiverUUID, req.amount)
    }
}