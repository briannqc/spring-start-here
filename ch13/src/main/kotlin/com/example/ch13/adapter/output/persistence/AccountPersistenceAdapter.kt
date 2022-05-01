package com.example.ch13.adapter.output.persistence

import com.example.ch13.application.domain.Account
import com.example.ch13.port.output.FindAccountByIdPort
import com.example.ch13.port.output.FindAllAccountsPort
import com.example.ch13.port.output.SaveAccountPort
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*

@Repository
class AccountPersistenceAdapter(
    private val jdbc: JdbcTemplate,
) : FindAccountByIdPort, SaveAccountPort, FindAllAccountsPort {

    override fun findAccountByIdPort(uuid: UUID): Account {
        val sql = "SELECT uuid, name, amount FROM accounts WHERE uuid = ?"
        return this.jdbc.queryForObject(sql, AccountRowMapper(), uuid)!!
    }

    override fun saveAccount(account: Account) {
        val sql = "UPDATE accounts SET amount = ? WHERE uuid = ?"
        this.jdbc.update(sql, account.getAmount(), account.uuid)
    }

    override fun findAllAccounts(): List<Account> {
        val sql = "SELECT uuid, name, amount FROM accounts"
        return this.jdbc.query(sql, AccountRowMapper())
    }
}

private class AccountRowMapper : RowMapper<Account> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Account {
        return Account(
            UUID.fromString(rs.getString("uuid")),
            rs.getString("name"),
            rs.getBigDecimal("amount")
        )
    }
}