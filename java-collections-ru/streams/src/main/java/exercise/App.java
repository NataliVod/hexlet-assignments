package exercise;

import com.sun.tools.javac.Main;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static int getCountOfFreeEmails(List<String> emails) {
        return (int) emails.stream()
                .filter(email -> isFreeEmail(email))
                .count();
    }

    public static boolean isFreeEmail(String email) {
        return email.contains("@gmail.com") || email.contains("@yandex.ru") || email.contains("@hotmail.com");
    }
}
// END
