package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
// Класс контроллера
public class WelcomeController {
    @GetMapping("/")
    public String welcome() {
        return String.format("Welcome to Spring");
    }

    @GetMapping("/hello")
    public String welcomeUser(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }
}
// END
