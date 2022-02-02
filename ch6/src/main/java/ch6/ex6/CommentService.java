package ch6.ex6;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CommentService {

    private static final Logger logger = Logger.getLogger(CommentService.class.getName());

    @Secured
    @ToLog
    public void publishComment(String text) {
        logger.info("Publishing comment: " + text);
    }
}
