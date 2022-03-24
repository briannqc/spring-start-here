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
import org.springframework.web.context.annotation.ApplicationScope
import org.springframework.web.context.annotation.RequestScope
import org.springframework.web.context.annotation.SessionScope
import java.util.concurrent.atomic.AtomicInteger

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
    private val loggedUserManagementService: LoggedUserManagementService,
    private val loginCountService: LoginCountService,
) {

    @GetMapping("/main")
    fun home(
        @RequestParam(required = false) logout: String?,
        model: Model,
    ): String {
        println(loggedUserManagementService)
        println(loginCountService)

        if (logout != null) {
            this.loggedUserManagementService.username = ""
        }

        val username = this.loggedUserManagementService.username
        val loginCount = this.loginCountService.getCount()
        if (username == "") {
            return "redirect:/"
        }
        model.addAttribute("username", username)
        model.addAttribute("loginCount", loginCount)
        return "welcome.html"
    }
}

@Component
@RequestScope
class LoginProcessor(
    private val loggedUserManagementService: LoggedUserManagementService,
    private val loginCountService: LoginCountService,
) {

    lateinit var username: String

    lateinit var password: String

    fun login(): Boolean {
        this.loginCountService.increase()

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

@Service
@ApplicationScope
class LoginCountService {

    private var count: AtomicInteger = AtomicInteger(0)

    fun increase() {
        this.count.incrementAndGet()
    }

    fun getCount(): Int {
        return this.count.get()
    }
}