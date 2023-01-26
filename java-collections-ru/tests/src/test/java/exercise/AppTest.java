package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> resultList1 = App.take(numbers1, 2);
        assertThat(resultList1).isEqualTo(Arrays.asList(1, 2));

        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        List<Integer> resultList2 = App.take(numbers2, 8);
        assertThat(resultList2).isEqualTo(Arrays.asList(7, 3, 10));
        // END
    }
}
