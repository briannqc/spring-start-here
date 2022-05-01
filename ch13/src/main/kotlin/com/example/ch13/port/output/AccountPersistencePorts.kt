package com.example.ch13.port.output

import com.example.ch13.application.domain.Account
import java.util.UUID

interface FindAccountByIdPort {

    fun findAccountByIdPort(uuid: UUID): Account
}

interface SaveAccountPort {

    fun saveAccount(account: Account)
}

interface FindAllAccountsPort {

    fun findAllAccounts(): List<Account>
}