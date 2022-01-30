package org.example.spring.ch5.ex6

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CommentProcessor(
    val commentRepository: CommentRepository,
)

interface CommentRepository

@Repository
class CommentRepositoryImpl : CommentRepository

@Service
class CommentServiceWrong(

    /**
     * Using prototype bean as a field in a singleton bean leads to race condition
     */
    val commentProcessor: CommentProcessor
)

@Service
class UserServiceWrong(
    val commentProcessor: CommentProcessor
)

@Service
class UserServiceCorrect(
    private val applicationContext: ApplicationContext
) {

    fun getCommentProcessor(): CommentProcessor {
        return this.applicationContext.getBean(CommentProcessor::class.java)
    }
}

@Component
@ComponentScan(basePackages = ["org.example.spring.ch5.ex6"])
open class ProjectConfiguration

class PrototypeBeanTest {

    @Test
    fun `a different instance of prototype bean is returned each time it is referred`() {
        val context = AnnotationConfigApplicationContext(ProjectConfiguration::class.java)
        val commentService = context.getBean(CommentServiceWrong::class.java)
        val userService = context.getBean(UserServiceWrong::class.java)

        assertThat(commentService.commentProcessor).isNotEqualTo(userService.commentProcessor)
        assertThat(commentService.commentProcessor.commentRepository)
            .isExactlyInstanceOf(CommentRepositoryImpl::class.java)
            .isEqualTo(userService.commentProcessor.commentRepository)
    }

    @Test
    fun `using prototype bean as a field in a singleton bean might lead to a race condition`() {
        val context = AnnotationConfigApplicationContext(ProjectConfiguration::class.java)
        val userService = context.getBean(UserServiceWrong::class.java)
        val userService2 = context.getBean(UserServiceWrong::class.java)

        assertThat(userService).isEqualTo(userService2)
        assertThat(userService.commentProcessor).isEqualTo(userService2.commentProcessor)
    }

    @Test
    fun `to use prototype bean in a singleton bean, inject ApplicationContext and get a fresh one everytime`() {
        val context = AnnotationConfigApplicationContext(ProjectConfiguration::class.java)
        val userService = context.getBean(UserServiceCorrect::class.java)

        assertThat(userService.getCommentProcessor()).isNotEqualTo(userService.getCommentProcessor())
    }
}