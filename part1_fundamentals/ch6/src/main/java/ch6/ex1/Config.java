package ch6.ex1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"ch6.ex1.comment", "ch6.ex1.aspect"})
@EnableAspectJAutoProxy
public class Config {
}
