package ch6ex1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* ch6ex1.comment.*.*(..))")
    public Object log(final ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Method: " + joinPoint.getSignature() + " will execute");
        final Object res = joinPoint.proceed();
        logger.info("Method: " + joinPoint.getSignature() + " executed");
        return res;
    }
}
