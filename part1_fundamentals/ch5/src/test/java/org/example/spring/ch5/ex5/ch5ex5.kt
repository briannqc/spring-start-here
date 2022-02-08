package org.example.spring.ch5.ex5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CommentService

@Configuration
@ComponentScan(basePackageClasses = [CommentService::class])
open class ProjectConfiguration

class PrototypeScopeTest {

    @Test
    fun `a different instance of prototype bean is returned each time it is referred`() {
        val context = AnnotationConfigApplicationContext(ProjectConfiguration::class.java)
        val cs1 = context.getBean(CommentService::class.java)
        val cs2 = context.getBean(CommentService::class.java)

        assertThat(cs1).isNotEqualTo(cs2)
    }
}