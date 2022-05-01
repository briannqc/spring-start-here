package com.example.ch13.adapter.input.web

import com.example.ch13.application.domain.Account
import com.example.ch13.port.input.GetAllAccountsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetAllAccountsController(
    private val getAllAccountsUseCase: GetAllAccountsUseCase
) {

    @GetMapping("/accounts")
    fun getAllAccounts(): List<Account> {
        return this.getAllAccountsUseCase.getAllAccounts()
    }
}