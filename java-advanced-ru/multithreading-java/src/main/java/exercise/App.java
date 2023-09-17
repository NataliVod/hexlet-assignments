package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public static void main(String[] args) {


    }

    // BEGIN


    public  static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        minThread.start();
        LOGGER.info("Thread minTread started");

        maxThread.start();
        LOGGER.info("Thread maxTread started");

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }
        LOGGER.info("Thread minTread finished");
        LOGGER.info("Thread maxTread finished");

        result.put("min", minThread.getMinNumber());
        result.put("max", maxThread.getMaxNumber());

        return result;
    }
    // END
}
