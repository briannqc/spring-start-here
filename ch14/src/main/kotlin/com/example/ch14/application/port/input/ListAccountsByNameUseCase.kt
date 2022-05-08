package com.example.ch14.application.port.input

import com.example.ch14.domain.Account

interface ListAccountsByNameUseCase {

    fun listAccountsByName(name: String): List<Account>
}