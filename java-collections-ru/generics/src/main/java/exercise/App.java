package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> result = new ArrayList<>();
        var length = where.size();
        for (Map<String, String> book: books) {
            var count = 0;
            for (Map.Entry<String, String> pair: where.entrySet()) {
                if (pair.getValue().equals(book.get(pair.getKey()))) {
                    count++;
                }
            }
            if (count == length) {
                result.add(book);
            }
        }
        return result;

    }
}
//END
