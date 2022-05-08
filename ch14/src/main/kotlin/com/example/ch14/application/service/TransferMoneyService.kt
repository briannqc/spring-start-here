package com.example.ch14.application.service

import com.example.ch14.application.port.input.TransferMoneyUseCase
import com.example.ch14.application.port.output.AccountPersistencePort
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import javax.transaction.Transactional

@Service
class TransferMoneyService(
    private val accountPersistencePort: AccountPersistencePort
) : TransferMoneyUseCase {

    @Transactional
    override fun transferMoney(
        senderUUID: UUID,
        receiverUUID: UUID,
        amount: BigDecimal,
    ) {
        val sender = this.accountPersistencePort.findAccountByUUID(senderUUID)
        val receiver = this.accountPersistencePort.findAccountByUUID(receiverUUID)
        sender.withdraw(amount)
        receiver.deposit(amount)
        this.accountPersistencePort.saveAccount(sender)
        this.accountPersistencePort.saveAccount(receiver)
    }
}