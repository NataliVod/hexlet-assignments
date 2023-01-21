package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class  App {

    public  static boolean scrabble(String letters, String word) {
        char[] letterArray = letters.toCharArray();
        char[] wordArray = word.toLowerCase().toCharArray();
        List<Character> letterList = new ArrayList<>();
        List<Character> wordList = new ArrayList<>();

        for (char letter: letterArray) {
            letterList.add(letter);
        }

        for (char letter: wordArray) {
           wordList.add(letter);
        }

        var wordLength = wordList.size();
        var count = 0;

        for (Character letter: wordList ) {
            if (letterList.contains(letter)) {
                letterList.remove(letter);
                count++;
            }
        }
        return count == wordLength;
    }
}
//END
