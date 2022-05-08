package com.example.ch14.application.port.input

import com.example.ch14.application.port.output.AccountPersistencePort
import com.example.ch14.application.service.ListAllAccountsUseCase
import com.example.ch14.domain.Account
import org.springframework.stereotype.Service

@Service
class ListAllAccountsService(
    private val accountPersistencePort: AccountPersistencePort
) : ListAllAccountsUseCase {

    override fun listAllAccounts(): List<Account> {
        return this.accountPersistencePort.findAllAccounts()
    }
}