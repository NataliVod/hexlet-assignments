package exercise;

import com.sun.source.tree.Tree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> result = new LinkedHashMap<>();
        Set<String> generalKeySet = new TreeSet<>();
        Set<String> keySet1 = map1.keySet();
        Set<String> keySet2 = map2.keySet();
        generalKeySet.addAll(keySet1);
        generalKeySet.addAll(keySet2);
        for (String key : generalKeySet) {
            if ((keySet1.contains(key)) && !(keySet2.contains(key))) {
               result.put(key,"deleted");
            }
            else if (!(keySet1.contains(key)) && (keySet2.contains(key))) {
                result.put(key,"added");
            }
            else if ((keySet1.contains(key) && keySet2.contains(key)) && !(map1.get(key).equals(map2.get(key)))) {
                result.put(key,"changed");
                }
            else if ((keySet1.contains(key) && keySet2.contains(key)) && (map1.get(key).equals(map2.get(key))))  {
                result.put(key,"unchanged");
                }
            }

        return result;
        }


}
//END
