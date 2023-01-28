package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
/*class App {

    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .flatMap(Stream::of)
                .map(str -> new String[]{str, str})
                .flatMap(Stream::of)
                .toArray(String[][]::new);
    }
}
// END
