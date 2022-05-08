package com.example.ch14.adapter.output.persistence

import com.example.ch14.application.port.output.AccountPersistencePort
import com.example.ch14.domain.Account
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountPersistenceAdapter(
    private val accountRepository: AccountRepository
) : AccountPersistencePort {

    override fun findAccountByUUID(uuid: UUID): Account {
        return this.accountRepository
            .findById(uuid)
            .map { e -> Account(e.uuid, e.name, e.amount) }
            .orElseThrow { IllegalArgumentException("account with uuid: $uuid not found") }
    }

    override fun saveAccount(account: Account) {
        this.accountRepository.save(AccountEntity(account.uuid, account.name, account.getAmount()))
    }

    override fun findAllAccounts(): List<Account> {
        return this.accountRepository
            .findAll()
            .map { e -> Account(e.uuid, e.name, e.amount) }
    }

    override fun findAccountsByName(name: String): List<Account> {
        return this.accountRepository
            .findAllByName(name)
            .map { e -> Account(e.uuid, e.name, e.amount) }
    }
}