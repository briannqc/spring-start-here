package com.example.ch14.application.port.output

import com.example.ch14.domain.Account
import java.util.*

interface AccountPersistencePort {

    fun findAccountByUUID(uuid: UUID): Account

    fun saveAccount(account: Account)

    fun findAllAccounts(): List<Account>

    fun findAccountsByName(name: String): List<Account>
}