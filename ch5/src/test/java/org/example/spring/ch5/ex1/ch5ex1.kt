package org.example.spring.ch5.ex1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class Dog

@Configuration
open class ProjectConfig {

    @Bean
    open fun dog(): Dog {
        return Dog()
    }

    @Bean
    open fun dog2(): Dog {
        return Dog()
    }
}

class SpringTest {

    @Test
    fun `declaring singleton-scoped beans with @Bean`() {
        val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
        val dog1 = context.getBean("dog", Dog::class.java)
        val dog2 = context.getBean("dog", Dog::class.java)

        assertThat(dog1).isEqualTo(dog2)
    }

    @Test
    fun `unlike singleton pattern, singleton scope in Spring is per bean name, not per bean type`() {
        val context = AnnotationConfigApplicationContext(ProjectConfig::class.java)
        val dog1 = context.getBean("dog")
        val dog2 = context.getBean("dog2")

        assertThat(dog1).isExactlyInstanceOf(Dog::class.java)
        assertThat(dog2).isExactlyInstanceOf(Dog::class.java)
        assertThat(dog1).isNotEqualTo(dog2)
    }
}