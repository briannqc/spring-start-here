package example.ch3.ex4;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class Ch3Ex4Test {

    @Test
    void testAutowiredField() {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var parrot = context.getBean(Parrot.class);
        var person = context.getBean(Person.class);

        assertThat(person.getName()).isEqualTo("Ella");
        assertThat(person.getParrot()).isEqualTo(parrot);
    }
}
