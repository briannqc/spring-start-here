package com.example.ch13.port.input

import com.example.ch13.application.domain.Account

interface GetAllAccountsUseCase {

    fun getAllAccounts(): List<Account>
}