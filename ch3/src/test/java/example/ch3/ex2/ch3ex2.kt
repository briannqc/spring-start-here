package example.ch3.ex2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

class Parrot(val name: String)

class Person(val name: String, val parrot: Parrot?)

@Configuration
open class ProjectConfig {

    @Bean
    open fun parrot(): Parrot {
        val p = Parrot("Koko")
        println("parrot is called, returning: $p. This method is called once only, no matter how many times it is invoked")
        return p
    }

    @Bean
    @Primary
    open fun person(): Person {
        return Person("Mina", parrot())
    }

    @Bean
    open fun person2(): Person {
        return Person("Tom", parrot())
    }
}

class Chapter3Test {

    @Test
    fun `wire beans by calling @Bean methods from @Configuration class`() {
        val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
        val parrot = context.getBean(Parrot::class.java)
        assertThat(parrot.name).isEqualTo("Koko")

        val person = context.getBean(Person::class.java)
        assertThat(person.name).isEqualTo("Mina")

        assertThat(person.parrot).isEqualTo(parrot)
    }
}