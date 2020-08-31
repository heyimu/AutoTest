package Hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SampleController {
    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

    @GetMapping
    public String hello() {
        String name = "hele";
        return String.format("Hello %s!",name );
    }

}
