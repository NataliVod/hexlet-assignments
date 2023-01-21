package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static void main (String[] args) {
        String sentence = "word text dog apple word apple word";
        Map words = getWordCount(sentence);
        System.out.println(toString(words));

    }
    public static Map getWordCount(String sentence) {
        String[] subStrings = sentence.split(" ");
        Map<String, Integer> result = new HashMap<>();
        var length = subStrings.length;
        if (sentence.length() == 0) {
            return result;
        }
        for (var i = 0; i < length; i++) {
            var wordsNumber = 0;
            for (var j = 0; j < length; j++) {
                if (subStrings[i].equals(subStrings[j])) {
                    wordsNumber++;
                }
                result.put(subStrings[i], wordsNumber);
            }
        }
        return  result;
    }
    public static String toString (Map<String, Integer> words) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map.Entry<String, Integer> word: words.entrySet() ) {
            result.append ("  " + word.getKey() + ": " + word.getValue() + "\n");
        }
        result.append( "}");
        return result.toString() ;
    }
}
//END
