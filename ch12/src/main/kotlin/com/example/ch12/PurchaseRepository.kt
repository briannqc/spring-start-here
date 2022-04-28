package com.example.ch12

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

interface PurchaseRepository {
    fun storePurchase(purchase: Purchase)
    fun findAllPurchases(): List<Purchase>
}

@Repository
class PurchaseRepositoryImpl(
    private val jdbc: JdbcTemplate,
) : PurchaseRepository {

    override fun storePurchase(purchase: Purchase) {
        val sql = "INSERT INTO purchases VALUES (NULL, ?, ?)"
        this.jdbc.update(sql, purchase.product, purchase.price)
    }

    override fun findAllPurchases(): List<Purchase> {
        val sql = "SELECT * FROM purchases"
        val purchaseRowMapper = RowMapper<Purchase> { r: ResultSet, _: Int ->
            Purchase(
                r.getInt("id"),
                r.getString("product"),
                r.getBigDecimal("price")
            )
        }
        return this.jdbc.query(sql, purchaseRowMapper)
    }
}