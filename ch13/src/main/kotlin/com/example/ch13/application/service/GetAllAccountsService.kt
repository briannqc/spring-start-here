package com.example.ch13.application.service

import com.example.ch13.application.domain.Account
import com.example.ch13.port.input.GetAllAccountsUseCase
import com.example.ch13.port.output.FindAllAccountsPort
import org.springframework.stereotype.Service

@Service
class GetAllAccountsService(
    private val findAllAccountsPort: FindAllAccountsPort
) : GetAllAccountsUseCase {

    override fun getAllAccounts(): List<Account> {
        return this.findAllAccountsPort.findAllAccounts()
    }
}