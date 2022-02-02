package ch6.ex4;

import ch6.ex4.comment.Comment;
import ch6.ex4.comment.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var service = context.getBean(CommentService.class);
        service.publishComment(new Comment("Hello AOP", "Brian"));
        service.deleteComment(new Comment("Another comment", "Anonymous"));
    }
}
