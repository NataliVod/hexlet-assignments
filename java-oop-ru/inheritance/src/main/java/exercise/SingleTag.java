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
    result.append("<").append(getTagName()).append(getAttrubuteString());
    return (result + "").trim() + ">";
    }
}
// END
