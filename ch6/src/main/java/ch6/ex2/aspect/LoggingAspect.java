package ch6.ex2.aspect;

import ch6.ex2.comment.Comment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* ch6.ex2.comment.*.*(..))")
    public Object log(final ProceedingJoinPoint joinPoint) throws Throwable {
        var signature = joinPoint.getSignature();
        var originalArgs = joinPoint.getArgs();

        logger.info("Method: " + signature +
                " with args: " + Arrays.toString(originalArgs) +
                " will execute");

        var interceptedArgs = new Object[]{new Comment("I got some comment, but I changed it under the table", "LoggingAspect")};
        final Object returnedByMethod = joinPoint.proceed(interceptedArgs);

        logger.info("Method: " + joinPoint.getSignature().getName() + " executed, returned value: " + returnedByMethod);

        return "FAILED";
    }
}
