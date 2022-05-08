package com.example.ch14.adapter.output.persistence

import org.springframework.data.repository.CrudRepository
import java.util.*

interface AccountRepository : CrudRepository<AccountEntity, UUID> {

    fun findAllByName(name: String): List<AccountEntity>
}