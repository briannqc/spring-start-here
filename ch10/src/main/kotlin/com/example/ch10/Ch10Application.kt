package com.example.ch10

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Ch10Application

fun main(args: Array<String>) {
    runApplication<Ch10Application>(*args)
}

@Controller
class HelloController {

    @GetMapping("/v1/hello")
    @ResponseBody
    fun hello(): String {
        return "Hello! with @Controller and @ResponseBody"
    }
}

@RestController
class HelloControllerV2 {

    @GetMapping("/v2/hello")
    fun hello(): String {
        return "Hello v2 with @RestController!"
    }
}

data class Country(
    val name: String,
    val population: Int,
)

@RestController
class CountryController {

    @GetMapping("/france")
    fun france(): Country {
        return Country("France", 67)
    }

    @GetMapping("/asia")
    fun asia(): List<Country> {
        return listOf(
            Country("Vietnam", 90),
            Country("Singapore", 6)
        )
    }

    @GetMapping("vietnam")
    fun vietnam(): ResponseEntity<Country> {
        val vn = Country("Vietnam", 90)
        return ResponseEntity
            .status(HttpStatus.OK)
            .header("continent", "Asia")
            .header("capital", "Hanoi")
            .body(vn)
    }
}