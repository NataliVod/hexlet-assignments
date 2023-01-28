package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {

    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .map(row -> Arrays.stream(row)
                        .flatMap(x -> Stream.of(x,x))
                        .toArray(String[]::new))
                        .flatMap(y -> Stream.of(y,y))
                        .toArray(String[][]::new);
    }
}
// END
