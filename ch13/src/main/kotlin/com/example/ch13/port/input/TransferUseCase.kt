package com.example.ch13.port.input

import java.math.BigDecimal
import java.util.UUID

interface TransferUseCase {

    fun transferMoney(
        senderId: UUID,
        receiverId: UUID,
        amount: BigDecimal,
    )
}
