package example.ch3.ex8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

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

@Configuration
open class ProjectConfigCase2 {

    @Bean
    open fun parrot1(): Parrot {
        return Parrot("Koko")
    }

    @Bean
    open fun parrot2(): Parrot {
        return Parrot("Messi")
    }

    @Bean
    open fun person(@Qualifier("parrot1") parrot: Parrot): Person {
        return Person(parrot)
    }
}


@Configuration
open class ProjectConfigCase3 {

    @Bean
    open fun parrot1(): Parrot {
        return Parrot("Koko")
    }

    @Bean
    @Primary
    open fun parrot2(): Parrot {
        return Parrot("Miki")
    }

    @Bean
    open fun person(parrot: Parrot): Person {
        return Person(parrot)
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

    @Test
    @DisplayName(
        """
        GIVEN there are multiple parrot beans in the context
        AND the parameter is annotated with @Qualifier
        THEN that bean with qualified name is used
    """
    )
    fun `using @Qualifier`() {
        val context = AnnotationConfigApplicationContext(ProjectConfigCase2::class.java)
        val person = context.getBean(Person::class.java)

        assertThat(person.parrot.name).isEqualTo("Koko")
    }

    @Test
    @DisplayName(
        """
        GIVEN there are multiple parrot beans in the context
        AND one is annotated @Primary
        THEN the primary bean is used
    """
    )
    fun `using @Primary`() {
        val context = AnnotationConfigApplicationContext(ProjectConfigCase3::class.java)
        val person = context.getBean(Person::class.java)

        assertThat(person.parrot.name).isEqualTo("Miki")
    }
}