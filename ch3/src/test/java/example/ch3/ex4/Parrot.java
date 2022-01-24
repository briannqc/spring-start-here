package example.ch3.ex4;

import org.springframework.stereotype.Component;

@Component
public class Parrot {

    private String name = "Koko";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
