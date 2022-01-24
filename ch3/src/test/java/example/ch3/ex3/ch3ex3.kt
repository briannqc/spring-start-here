package example.ch3.ex3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class Parrot(val name: String)

class Person(val name: String, val parrot: Parrot)

@Configuration
open class ProjectConfig {

    @Bean
    open fun parrot(): Parrot {
        return Parrot("Messi")
    }

    @Bean
    open fun person(
        parrot: Parrot
    ): Person {
        return Person("Salah", parrot)
    }
}

class Chapter3Test {

    @Test
    fun `wire beans by setting @Bean method parameter`() {
        val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
        val parrot = context.getBean(Parrot::class.java)
        assertThat(parrot.name).isEqualTo("Messi")

        val person = context.getBean(Person::class.java)
        assertThat(person.name).isEqualTo("Salah")
        assertThat(person.parrot).isEqualTo(parrot)
    }
}