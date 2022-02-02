package ch6.ex6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var service = ctx.getBean(CommentService.class);
        service.publishComment("Hi there!");
    }
}
