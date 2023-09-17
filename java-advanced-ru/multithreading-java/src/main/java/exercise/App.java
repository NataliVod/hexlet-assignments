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
            LOGGER.info("Thread minTread finished");

            maxThread.join();
            LOGGER.info("Thread maxTread finished");

        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }

       result = Map.of(
                "min", minThread.getMinNumber(),
                "max", maxThread.getMaxNumber()
        );

        LOGGER.log(Level.INFO, "Result: " + result.toString());

        return result;
    }
    // END
}
