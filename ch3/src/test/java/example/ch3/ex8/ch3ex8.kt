package example.ch3.ex8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class Parrot(val name: String)

class Person(val parrot: Parrot)

@Configuration
open class ProjectConfigCase1 {

    @Bean
    open fun parrot1(): Parrot {
        return Parrot("Koko")
    }

    @Bean
    open fun parrot2(): Parrot {
        return Parrot("Messi")
    }

    @Bean
    open fun person(parrot2: Parrot): Person {
        return Person(parrot2)
    }
}

class Ch3Ex8Test {

    @Test
    @DisplayName(
        """
        GIVEN there are multiple parrot beans in the context
        AND the parameter name is the same as one of those beans name
        THEN that bean is used
    """
    )
    fun `parameter name is same as bean name`() {
        val context = AnnotationConfigApplicationContext(ProjectConfigCase1::class.java)
        val person = context.getBean(Person::class.java)

        assertThat(person.parrot.name).isEqualTo("Messi")
    }
}