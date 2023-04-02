package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        List<String> result = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                var value = field.get(object);
                if (field.isAnnotationPresent(NotNull.class) && (value == null)) {

                    result.add(field.getName());
                }
            }
            catch (IllegalAccessException e) {
                System.out.println(e.getMessage());}
        }

        return result;


    }
}
// END
