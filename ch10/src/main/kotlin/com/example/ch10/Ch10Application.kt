package com.example.ch10

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
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