package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) throws IOException {
        String carJson = car.serialize();
        Files.write(path, carJson.getBytes());
    }

    public static Car extract (Path path) throws IOException {
        String carJson = Files.readString(path);
        return Car.unsertialize(carJson) ;
    }
}
// END
