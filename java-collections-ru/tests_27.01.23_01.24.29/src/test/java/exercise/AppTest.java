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
        List<Integer> testList= List.of(1,2,3,4);
        List<Integer> resultList = App.take(testList, 2);
        assertThat(resultList).isEqualTo(List.of(1,2));
        // END
    }
}
