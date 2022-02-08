package ch6.ex2;

import ch6.ex2.comment.Comment;
import ch6.ex2.comment.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var service = context.getBean(CommentService.class);
        var result = service.publishComment(new Comment("Hello AOP", "Brian"));
        logger.info("After publishComment(), result: " + result);
    }
}
