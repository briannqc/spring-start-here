package ch6.ex1;

import ch6.ex1.comment.Comment;
import ch6.ex1.comment.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var service = context.getBean(CommentService.class);
        service.publishComment(new Comment("Hello AOP", "Brian"));
    }
}
