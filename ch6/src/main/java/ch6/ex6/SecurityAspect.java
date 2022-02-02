package ch6.ex6;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class SecurityAspect {

    private static final Logger logger = Logger.getLogger(SecurityAspect.class.getName());

    @Around("@annotation(Secured)")
    public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("BEFORE: Securing method: " + joinPoint);
        var result = joinPoint.proceed();
        logger.info("AFTER: Securing method: " + joinPoint);
        return result;
    }
}
