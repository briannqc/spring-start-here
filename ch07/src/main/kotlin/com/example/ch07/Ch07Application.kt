package com.example.ch07

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@SpringBootApplication
class Ch07Application

fun main(args: Array<String>) {
	runApplication<Ch07Application>(*args)
}

@Controller
class MainController {

	@RequestMapping("/home")
	fun home(): String = "home.html"

	@RequestMapping("/test")
	fun test(): String = "test.txt"
}