package example.ch4.ex6

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
class CommentService(
    private val commentRepository: CommentRepository,
    @Qualifier("email") private val commentNotificationProxy: CommentNotificationProxy,
) {

    fun publishComment(comment: Comment) {
        this.commentRepository.storeComment(comment)
        this.commentNotificationProxy.sendComment(comment)
    }
}

@Component
class AnotherService(
    @Qualifier("push") private val commentNotificationProxy: CommentNotificationProxy,
) {

    fun publishComment(comment: Comment) {
        println("Message from AnotherService: I'm doing another task but still need sendComment via EMAIL")
        this.commentNotificationProxy.sendComment(comment)
    }
}

data class Comment(
    val author: String,
    val text: String,
)

interface CommentRepository {
    fun storeComment(comment: Comment)
}

@Component
class DBCommentRepository : CommentRepository {

    override fun storeComment(comment: Comment) {
        println("Storing comment: ${comment.text} by ${comment.author}")
    }
}

interface CommentNotificationProxy {
    fun sendComment(comment: Comment)
}

@Component
@Qualifier("email")
class EmailCommentNotificationProxy : CommentNotificationProxy {

    override fun sendComment(comment: Comment) {
        println("Notifying comment via Email: ${comment.text} by ${comment.author}")
    }
}

@Component
@Qualifier("push")
class CommentPushNotificationProxy : CommentNotificationProxy {

    override fun sendComment(comment: Comment) {
        println("Notifying comment via Pushing notification: ${comment.text} by ${comment.author}")
    }
}

@Configuration
@ComponentScan(basePackages = ["example.ch4.ex6"])
open class ProjectConfiguration

fun main() {
    val context = AnnotationConfigApplicationContext(ProjectConfiguration::class.java)
    val commentService = context.getBean(CommentService::class.java)
    val anotherService = context.getBean(AnotherService::class.java)

    val comment = Comment("Brian", "I'm reading Spring Start Here, the book is a good refresher")
    commentService.publishComment(comment)
    anotherService.publishComment(comment)
}