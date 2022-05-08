package com.example.ch14.application.port.input

import java.math.BigDecimal
import java.util.UUID

interface TransferMoneyUseCase {

    fun transferMoney(senderUUID: UUID, receiverUUID: UUID, amount: BigDecimal)
}