package ch6ex1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"ch6ex1.comment", "ch6ex1.aspect"})
@EnableAspectJAutoProxy
public class Config {
}
