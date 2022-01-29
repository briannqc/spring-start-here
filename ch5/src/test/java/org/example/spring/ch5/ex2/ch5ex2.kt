package org.example.spring.ch5.ex2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class CommentService(
    val commentRepository: CommentRepository
)

@Service
class UserService(
    val commentRepository: CommentRepository
)

interface CommentRepository

@Repository
class CommentRepositoryImpl : CommentRepository

@Configuration
@ComponentScan(basePackages = ["org.example.spring.ch5.ex2"])
open class ProjectConfiguration

class SingletonStereotypeTest {

    @Test
    fun `declaring singleton beans using stereotype annotations`() {
        val context = AnnotationConfigApplicationContext(ProjectConfiguration::class.java)
        val commentService = context.getBean(CommentService::class.java)
        val userService = context.getBean(UserService::class.java)

        assertThat(commentService.commentRepository)
            .isExactlyInstanceOf(CommentRepositoryImpl::class.java)
            .isEqualTo(userService.commentRepository)
    }
}