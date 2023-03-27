package exercise;
hexlet
import java.util.Map;

// BEGIN
public class Tag {
    String tagName;
    Map<String, String> attributes;

    public Tag (String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }
    public String toString(){
        return "";
    }
}
// END
