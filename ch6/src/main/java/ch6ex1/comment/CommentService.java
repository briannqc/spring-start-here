package ch6ex1.comment;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CommentService {

    private static final Logger logger = Logger.getLogger(CommentService.class.getName());

    public void publishComment(final Comment comment) {
        logger.info("Publishing comment: " + comment.getText() + " from: " + comment.getAuthor());
    }
}
