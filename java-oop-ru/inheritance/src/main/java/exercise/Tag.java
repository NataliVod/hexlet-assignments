package exercise;

import java.util.Map;

// BEGIN
public class Tag {
    private String tagName;
    private Map<String, String> attributes;

    public Tag (String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    public String getTagName() {
        return tagName;
    }

    public Map<String, String> getAttributes() {
        return  attributes;
    }

    public String getAttrubuteString() {
        if (attributes.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (var attribute : attributes.entrySet()) {
            result.append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\" ");
        }
        return " " + result.toString().trim();
    }
}
// END
