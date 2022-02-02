package ch6.ex4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("@annotation(ToLog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("BEFORE " + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        logger.info("AFTER " + joinPoint.getSignature());
        return result;
    }

    @Before("execution(* ch6.ex4.comment.CommentService.publishComment(..))")
    public void before() {
        logger.info("We can also use @Before, with is less flexible than @Around but simpler too");
    }

    @AfterReturning(
            value = "execution(* ch6.ex4.comment.CommentService.publishComment(..))",
            returning = "returnedValue"
    )
    public void afterReturning(Object returnedValue) {
        logger.info("Spring also support @After, @AfterReturning and @AfterThrowing");
        logger.info("We can capture returned value in @AfterReturning with 'returning' param, it was: " + returnedValue + " here");
    }
}
