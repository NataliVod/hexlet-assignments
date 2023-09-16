package exercise;

import java.time.LocalDateTime;
import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;

// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        var time = LocalDateTime.now().getHour();
        return switch (time) {
        case 6, 7 , 8, 9, 10 ,11 -> new Morning();
        case 12, 13, 14, 15, 16, 17 -> new Day();
        case 18, 19, 20, 21, 22 -> new Evening();
            default -> new Night();
        };
    }
}
// END
