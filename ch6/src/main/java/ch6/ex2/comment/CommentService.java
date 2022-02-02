package ch6.ex2.comment;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CommentService {

    private static final Logger logger = Logger.getLogger(CommentService.class.getName());

    public String publishComment(final Comment comment) {
        logger.info("Publishing comment: " + comment.getText() + " from: " + comment.getAuthor());
        return "SUCCESS";
    }
}
