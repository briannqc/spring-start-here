package com.example.ch09

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.annotation.RequestScope

@SpringBootApplication
class Ch09Application

fun main(args: Array<String>) {
    runApplication<Ch09Application>(*args)
}

@Controller
class LoginController(
    private val loginProcessor: LoginProcessor
) {

    @GetMapping("/")
    fun loginGet(): String {
        return "login.html"
    }

    @PostMapping("/")
    fun loginPost(
        @RequestParam username: String,
        @RequestParam password: String,
        model: Model,
    ): String {
        loginProcessor.username = username
        loginProcessor.password = password
        println(loginProcessor)

        val loggedIn = loginProcessor.login()
        if (loggedIn) {
            model.addAttribute("message", "You are logged in!")
        } else {
            model.addAttribute("message", "Login failed!")
        }
        return "login.html"
    }
}

@Component
@RequestScope
class LoginProcessor {

    lateinit var username: String

    lateinit var password: String

    fun login(): Boolean {
        return "nataline" == this.username && "password" == this.password
    }
}