package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{

    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName, attributes);
    }

@Override
    public String toString(){
    StringBuilder result = new StringBuilder();
    result.append("<").append(tagName).append(" ");
    for (var attribute : attributes.entrySet()) {
        result.append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\" ");
    }
    return result.toString().trim() + ">";
    }
}
// END
