package com.example.ch8

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@SpringBootApplication
class Ch8Application

@Controller
class GreetingController {

	@RequestMapping("/greeting/{username}")
	fun greeting(
		@PathVariable username: String,
		@RequestParam color: String,
		page: Model,
	): String {
		page.addAttribute("username", username)
		page.addAttribute("color", color)
		return "greeting.html"
	}
}

fun main(args: Array<String>) {
	runApplication<Ch8Application>(*args)
}
