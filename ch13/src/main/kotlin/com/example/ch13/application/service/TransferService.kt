package com.example.ch13.application.service

import com.example.ch13.port.input.TransferUseCase
import com.example.ch13.port.output.FindAccountByIdPort
import com.example.ch13.port.output.SaveAccountPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

@Service
class TransferService(
    private val findAccountByIdPort: FindAccountByIdPort,
    private val saveAccountPort: SaveAccountPort,
) : TransferUseCase {

    @Transactional
    override fun transferMoney(
        senderId: UUID,
        receiverId: UUID,
        amount: BigDecimal,
    ) {
        val sourceAccount = this.findAccountByIdPort.findAccountByIdPort(senderId)
        val destinationAccount = this.findAccountByIdPort.findAccountByIdPort(receiverId)
        sourceAccount.withdraw(amount)
        destinationAccount.deposit(amount)
        this.saveAccountPort.saveAccount(sourceAccount)
        this.saveAccountPort.saveAccount(destinationAccount)
    }
}