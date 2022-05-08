package com.example.ch14.adapter.input.web

import com.example.ch14.application.port.input.ListAccountsByNameUseCase
import com.example.ch14.application.service.ListAllAccountsUseCase
import com.example.ch14.domain.Account
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ListAccountsController(
    private val listAccountsUseCase: ListAllAccountsUseCase,
    private val listAccountsByNameUseCase: ListAccountsByNameUseCase
) {

    @GetMapping("/accounts")
    fun listAccounts(
        @RequestParam(required = false) name: String?
    ): List<Account> {
        return if (name == null) {
            this.listAccountsUseCase.listAllAccounts()
        } else {
            this.listAccountsByNameUseCase.listAccountsByName(name)
        }
    }
}