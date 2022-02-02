package ch6.ex4.comment;

import ch6.ex4.aspect.ToLog;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CommentService {

    private static final Logger logger = Logger.getLogger(ch6.ex2.comment.CommentService.class.getName());

    public void publishComment(Comment comment) {
        logger.info("Publishing comment: " + comment);
    }

    @ToLog
    public void deleteComment(Comment comment) {
        logger.info("Deleting comment: " + comment);
    }
}
