package exercise;

import java.util.Map;
import java.util.List;


// BEGIN
public class PairedTag extends Tag {
    private String tagBody;
    private List<Tag> children;

    public String getTagBody() {
        return tagBody;
    }

    public List getChildren() {
        return children;
    }


    public PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> children) {
        super(tagName, attributes);
        this.tagBody = tagBody;
        this.children = children;
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("<").append(getTagName()).append(getAttrubuteString())
                .append(">").append(tagBody).append(getChildString())
                .append("</").append(getTagName()).append(">");
        return result.toString();
    }

    private String getChildString() {
        if (children == null || children.size() ==0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        if (!tagBody.equals("")) {
            result.append(" ");
        }
        for (var child: children){
            result.append(child.toString());
        }
        return result + "";
    }
}
// END
