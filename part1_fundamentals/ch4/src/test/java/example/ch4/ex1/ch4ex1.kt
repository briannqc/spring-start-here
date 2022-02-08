package example.ch4.ex1

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

class DBCommentRepository : CommentRepository {

    override fun storeComment(comment: Comment) {
        println("Storing comment: ${comment.text} by ${comment.author}")
    }
}

interface CommentNotificationProxy {
    fun sendComment(comment: Comment)
}

class EmailCommentNotificationProxy : CommentNotificationProxy {

    override fun sendComment(comment: Comment) {
        println("Notifying comment: ${comment.text} by ${comment.author}")
    }
}

fun main() {
    val commentRepository = DBCommentRepository()
    val commentNotificationProxy = EmailCommentNotificationProxy()
    val commentService = CommentService(commentRepository, commentNotificationProxy)

    val comment = Comment("Brian", "I'm reading Spring Start Here, the book is a good refresher")
    commentService.publishComment(comment)
}