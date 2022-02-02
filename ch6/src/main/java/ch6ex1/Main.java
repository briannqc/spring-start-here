package ch6ex1;

import ch6ex1.comment.Comment;
import ch6ex1.comment.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var service = context.getBean(CommentService.class);
        service.publishComment(new Comment("Hello AOP", "Brian"));
    }
}
