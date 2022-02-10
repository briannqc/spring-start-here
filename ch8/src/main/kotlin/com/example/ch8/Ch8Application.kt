package com.example.ch8

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

@Controller
class ProductController(
	private val productService: ProductService
) {

	@GetMapping("/products")
	fun viewProducts(model: Model): String {
		val products = productService.findAll()
		model.addAttribute("products", products)
		return "products.html"
	}

	@PostMapping("/products")
	fun addProduct(
		@RequestParam name: String,
		@RequestParam price: Long,
		model: Model,
	): String {
		val product = Product(name, price)
		productService.add(product)

		val products = productService.findAll()
		model.addAttribute("products", products)
		return "products.html"
	}
}

interface ProductService {

	fun findAll(): List<Product>

	fun add(p: Product)
}

@Service
class NonThreadSafeProductService : ProductService {

	private val products: MutableList<Product> = mutableListOf()

	override fun findAll(): List<Product> {
		return products
	}

	override fun add(p: Product) {
		products.add(p)

	}
}

data class Product(
	val name: String,
	val price: Long,
)

fun main(args: Array<String>) {
	runApplication<Ch8Application>(*args)
}
