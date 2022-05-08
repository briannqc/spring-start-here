package com.example.ch14.adapter.output.persistence

import org.hibernate.annotations.Type
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "accounts")
data class AccountEntity(
    @Id @Type(type = "uuid-char") val uuid: UUID,
    val name: String,
    val amount: BigDecimal,
)