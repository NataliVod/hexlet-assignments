package exercise;

import java.util.Map;
import java.util.List;


// BEGIN
public class PairedTag extends Tag {
    String tagBody;
    List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> children) {
        super(tagName, attributes);
        this.tagBody = tagBody;
        this.children = children;
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("<").append(tagName).append(" ");
        for (var attribute : attributes.entrySet()) {
            result.append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\" ");
        }
        int length = result.length();
        result.delete(length - 1, length);
        result.append(">").append(tagBody);
        for (var child: children){
            result.append(child.toString());
        }
        result.append("</").append(tagName).append(">");

        return result.toString();
    }
}
// END
