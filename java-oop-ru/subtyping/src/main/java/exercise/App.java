package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue (KeyValueStorage keyValueStorage) {
        Map<String, String> storage = keyValueStorage.toMap();
        for (Map.Entry<String, String> pair: keyValueStorage.toMap().entrySet()) {
            keyValueStorage.unset(pair.getKey());
        }
        for (Map.Entry<String, String> pair: storage.entrySet()) {
            keyValueStorage.set(pair.getValue(), pair.getKey());
        }
    }
}
// END
