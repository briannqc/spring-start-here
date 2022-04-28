package com.example.ch12

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@SpringBootApplication
class Ch12Application

fun main(args: Array<String>) {
    runApplication<Ch12Application>(*args)
}

@Configuration
class ProjectConfig(
    @Value("\${custom.datasource.url}")
    val datasourceUrl: String,

    @Value("\${custom.datasource.username}")
    val datasourceUsername: String,

    @Value("\${custom.datasource.password}")
    val datasourcePassword: String
) {
    @Bean
    fun datasource(): DataSource {
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = datasourceUrl
        dataSource.username = datasourceUsername
        dataSource.password = datasourcePassword
        dataSource.connectionTimeout = 1000

        return dataSource
    }
}