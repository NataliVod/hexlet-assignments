package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private int[] numbers;
    private int minNumber;

    MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public void run() {
        minNumber = Arrays.stream(numbers).min().getAsInt();
    }
}
// END
