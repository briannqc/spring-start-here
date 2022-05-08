package com.example.ch14.application.service

import com.example.ch14.application.port.input.ListAccountsByNameUseCase
import com.example.ch14.application.port.output.AccountPersistencePort
import com.example.ch14.domain.Account
import org.springframework.stereotype.Service

@Service
class ListAccountsByNameService(
    private val accountPersistencePort: AccountPersistencePort
) : ListAccountsByNameUseCase {

    override fun listAccountsByName(name: String): List<Account> {
        return this.accountPersistencePort.findAccountsByName(name)
    }
}