package example.ch2.ex3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.*
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

class Dog(val name: String)

@Component
class Car

@Component
class CarWithPostConstruct {

    lateinit var model: String

    @PostConstruct
    fun init() {
        this.model = "SetInPostConstruct"
    }
}

@Configuration
@ComponentScan(basePackages = ["example.ch2.ex3"])
open class Config {

    @Bean
    open fun dog1(): Dog {
        return Dog("Nick")
    }

    @Bean
    @Primary
    open fun dog2(): Dog {
        return Dog("Snoopy")
    }
}

class StereotypeAnnotationTest {

    @Test
    @DisplayName(
        """
        GIVEN there are more than 1 beans of the same type
        AND one of them is annotated with @Primary
        WHEN getBean(type)
        THEN got the primary bean
    """
    )
    fun primaryBean() {
        val context = AnnotationConfigApplicationContext(Config::class.java)
        val dog = context.getBean(Dog::class.java)
        assertThat(dog.name).isEqualTo("Snoopy")
    }

    @Test
    @DisplayName(
        """
        GIVEN class A is annotated with @Component
        AND Configuration class is annotated with @ComponentScan(basePackages = [<package-of-A>]
        THEN Spring will automatically init a bean of type A and add the instance into Spring context
    """
    )
    fun componentAndComponentScan() {
        val context = AnnotationConfigApplicationContext(Config::class.java)
        val car = context.getBean(Car::class.java)
        assertThat(car).isNotNull
    }

    @Test
    @DisplayName(
        """
        GIVEN a component class has a @PostConstruct method
        THEN that method is invoked automatically
    """
    )
    fun componentWithPostConstruct() {
        val context = AnnotationConfigApplicationContext(Config::class.java)
        val car = context.getBean(CarWithPostConstruct::class.java)
        assertThat(car.model).isEqualTo("SetInPostConstruct")
    }
}
