package example.ch4.ex5

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
class CommentService(
    private val commentRepository: CommentRepository,
    private val commentNotificationProxy: CommentNotificationProxy,
) {

    fun publishComment(comment: Comment) {
        this.commentRepository.storeComment(comment)
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
class EmailCommentNotificationProxy : CommentNotificationProxy {

    override fun sendComment(comment: Comment) {
        println("Notifying comment via Email: ${comment.text} by ${comment.author}")
    }
}

@Component
@Primary
class CommentPushNotificationProxy : CommentNotificationProxy {

    override fun sendComment(comment: Comment) {
        println("Notifying comment via Pushing notification: ${comment.text} by ${comment.author}")
    }
}

@Configuration
@ComponentScan(basePackages = ["example.ch4.ex5"])
open class ProjectConfiguration

fun main() {
    val context = AnnotationConfigApplicationContext(ProjectConfiguration::class.java)
    val commentService = context.getBean(CommentService::class.java)

    val comment = Comment("Brian", "I'm reading Spring Start Here, the book is a good refresher")
    commentService.publishComment(comment)
}