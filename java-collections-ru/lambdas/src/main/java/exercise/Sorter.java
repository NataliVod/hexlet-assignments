package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted(Comparator.comparing(user -> getLocalDate(user)))
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
    }
    private static LocalDate getLocalDate(Map<String, String> user) {
        var birthday = user.get("birthday");
        String[] birthdayArray = birthday.split("-");
        var year = Integer.parseInt(birthdayArray[0]);
        var month = Integer.parseInt(birthdayArray[1]);
        var day = Integer.parseInt(birthdayArray[2]);
        return LocalDate.of(year, month, day);

    }
}
// END
