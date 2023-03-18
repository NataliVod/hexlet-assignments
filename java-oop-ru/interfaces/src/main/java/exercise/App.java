package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static void main(String[] args) {

    }
    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        List<String> result = apartments.stream()
                .sorted(Comparator.comparing(Home::getArea))
                .limit((long)n)
                .map((x) -> x.toString())
                .collect(Collectors.toList());
        return result;
    }
}
// END
