package example.ch2.ex8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinitionCustomizer
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Configuration
import java.util.function.Supplier

class Parrot(val name: String)

@Configuration
open class Config

class BeanRegisterTest {

    @Test
    fun registerBeanProgrammatically() {
        val context = AnnotationConfigApplicationContext(Config::class.java)
        context.registerBean("parrot", Parrot::class.java, Supplier { Parrot("Messi") })

        val p = context.getBean(Parrot::class.java)
        assertThat(p.name).isEqualTo("Messi")
    }

    @Test
    fun registerBeanProgrammaticallyWithCustomizer() {
        val context = AnnotationConfigApplicationContext(Config::class.java)
        context.registerBean(
            "parrot",
            Parrot::class.java,
            Supplier { Parrot("Messi") },
        )
        context.registerBean(
            "primaryParrot",
            Parrot::class.java,
            Supplier { Parrot("Miki") },
            BeanDefinitionCustomizer { bdc -> bdc.isPrimary = true },
        )

        val p = context.getBean(Parrot::class.java)
        assertThat(p.name).isEqualTo("Miki")
    }
}