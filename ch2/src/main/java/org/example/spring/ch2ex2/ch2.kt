package org.example.spring.ch2ex2

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class Parrot(
    val name: String
)

@Configuration
open class ProjectConfig {

    /**
     * By default, bean name is the method which returns it.
     */
    @Bean
    open fun parrot1(): Parrot {
        return Parrot("Koko")
    }

    /**
     * We can override bean name with @Bean annotation. Any of the
     * following syntaxes will change the name of the bean in "miki"
     *
     *   @Bean("miki")
     *   @Bean(value = ["miki"])
     *   @Bean(name = ["miki"])
     */
    @Bean("miki")
    open fun parrot2(): Parrot {
        return Parrot("Miki")
    }

    /**
     * When there are 2 beans with the same name, the one above will be used.
     */
    @Bean(value = ["riki"])
    open fun parrot4(): Parrot {
        return Parrot("Messi")
    }

    @Bean(name = ["riki"])
    open fun parrot3(): Parrot {
        return Parrot("Riki")
    }

    @Bean
    open fun hello(): String {
        return "Hello"
    }

    @Bean
    open fun ten(): Int {
        return 10
    }
}

fun main() {
    val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
    val p1 = context.getBean("parrot1", Parrot::class.java)
    println(p1.name)

    val p2 = context.getBean("miki", Parrot::class.java)
    println(p2.name)

    val p3 = context.getBean("riki", Parrot::class.java)
    println(p3.name)

    val s = context.getBean(String::class.java)
    println(s)

    val n = context.getBean(Int::class.java)
    println(n)
}