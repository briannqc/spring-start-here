package example.ch3.ex5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
class Parrot

@Component
class Person @Autowired constructor(
    val parrot: Parrot
)

@Configuration
@ComponentScan(basePackages = ["example.ch3.ex5"])
open class ProjectConfig

class Ch3Ex5Test {

    @Test
    fun `@Autowired constructor`() {
        val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
        val parrot = context.getBean(Parrot::class.java)
        val person = context.getBean(Person::class.java)

        assertThat(person.parrot).isEqualTo(parrot)
    }
}