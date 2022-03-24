package com.example.ch09

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.annotation.RequestScope
import org.springframework.web.context.annotation.SessionScope

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
            return "redirect:/main"
        }

        model.addAttribute("message", "Login failed!")
        return "login.html"
    }
}

@Controller
class MainController(
    private val loggedUserManagementService: LoggedUserManagementService
) {

    @GetMapping("/main")
    fun home(
        @RequestParam(required = false) logout: String?,
        model: Model,
    ): String {
        println(loggedUserManagementService)
        if (logout != null) {
            this.loggedUserManagementService.username = ""
        }

        val username = this.loggedUserManagementService.username
        if (username == "") {
            return "redirect:/"
        }
        model.addAttribute("username", username)
        return "welcome.html"
    }
}

@Component
@RequestScope
class LoginProcessor(
    private val loggedUserManagementService: LoggedUserManagementService
) {

    lateinit var username: String

    lateinit var password: String

    fun login(): Boolean {
        val loggedIn = "nataline" == this.username && "password" == this.password
        if (loggedIn) {
            this.loggedUserManagementService.username = username
        }
        return loggedIn
    }
}

@Service
@SessionScope
class LoggedUserManagementService {

    var username: String = ""
}