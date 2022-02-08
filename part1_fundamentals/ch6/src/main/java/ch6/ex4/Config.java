package ch6.ex4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"ch6.ex4.comment", "ch6.ex4.aspect"})
@EnableAspectJAutoProxy
public class Config {
}
