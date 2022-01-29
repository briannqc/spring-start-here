package org.example.spring.ch5.ex3

import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

class CommentService {

    init {
        println("An instance of CommentService is created")
    }
}

@Configuration
open class ProjectConfigurationEager {

    @Bean
    open fun commentService(): CommentService {
        return CommentService()
    }
}

@Configuration
open class ProjectConfigurationLazy {

    @Bean
    @Lazy
    open fun commentService(): CommentService {
        return CommentService()
    }
}

class Test {

    @Test
    fun `Spring eagerly instantiates beans by default`() {
        AnnotationConfigApplicationContext(ProjectConfigurationEager::class.java)
        // Stdout: An instance of CommentService is created
        println("We don't use CommentService bean but it is still created by default")
    }

    @Test
    fun `Use @Lazy to lazily instantiate beans`() {
        val context = AnnotationConfigApplicationContext(ProjectConfigurationLazy::class.java)
        println("There should be no commentService bean in the context until we need it")

        context.getBean(CommentService::class.java)
        println("After retrieving the CommentService bean")
    }
}