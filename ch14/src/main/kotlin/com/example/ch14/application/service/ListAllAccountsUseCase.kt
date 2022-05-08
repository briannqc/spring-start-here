package com.example.ch14.application.service

import com.example.ch14.domain.Account

interface ListAllAccountsUseCase {

    fun listAllAccounts(): List<Account>
}