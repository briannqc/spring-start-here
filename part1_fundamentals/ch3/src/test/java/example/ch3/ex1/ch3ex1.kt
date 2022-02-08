package example.ch3.ex1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class Parrot(val name: String)

class Person(
    val name: String,
    val parrot: Parrot?
)

@Configuration
open class ProjectConfig {

    @Bean
    open fun parrot(): Parrot {
        return Parrot("Koko")
    }

    @Bean
    open fun person(): Person {
        return Person("Mina", null)
    }
}

class Chapter3Test {

    @Test
    fun `beans in Spring context are not linked to one another by default`() {
        val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)

        val parrot = context.getBean(Parrot::class.java)
        assertThat(parrot).isNotNull
        assertThat(parrot.name).isEqualTo("Koko")

        val person = context.getBean(Person::class.java)
        assertThat(person).isNotNull
        assertThat(person.name).isEqualTo("Mina")

        assertThat(person.parrot).isNull()
    }
}