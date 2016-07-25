package com.github.osvadopina.hamcrest.jsonhal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvadopina.hamcrest.jsonhal.jsonpath.AbstractJsonValueMatcher;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HalEmbeddedMatcher extends HalDocumentPartMatcher {


    private AbstractJsonValueMatcher[] abstractJsonValueMatchers;

    private List<AbstractJsonValueMatcher> notMatched;

    public HalEmbeddedMatcher(AbstractJsonValueMatcher[] abstractJsonValueMatchers) {
        this.abstractJsonValueMatchers = abstractJsonValueMatchers;
    }


    @Override
    public void describeTo(Description description) {
        if (!notMatched.isEmpty()) {
            description.appendText("for uri : ");
            description.appendList("(", ") and (", ")", notMatched);
        }
    }

    @Override
    protected boolean matchesSafely(String item) {
        String halResource = getOnlyResourceFromDocument(item);

        notMatched = new ArrayList<AbstractJsonValueMatcher>();

        for (AbstractJsonValueMatcher abstractJsonValueMatcher : abstractJsonValueMatchers) {
            if (!abstractJsonValueMatcher.matches(halResource)) {
                notMatched.add(abstractJsonValueMatcher);
            }
        }
        return notMatched.isEmpty();
    }

    @Override
    protected void describeMismatchSafely(String actual, Description mismatchDescription) {
        if (!notMatched.isEmpty()) {
            mismatchDescription.appendText("for uri : ");
            Description matcherDescription;
            List<String> descriptions = new ArrayList<String>();
            for (AbstractJsonValueMatcher abstractJsonValueMatcher : notMatched) {
                matcherDescription = new StringDescription();
                abstractJsonValueMatcher.describeMismatch(getOnlyResourceFromDocument(actual), matcherDescription);
                descriptions.add(matcherDescription.toString());
            }
            mismatchDescription.appendText(getValueList("(", ") and (", ")", descriptions));
        }
    }

    private String getOnlyResourceFromDocument(String item) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, Object> parsedHal = mapper.readValue(item, Map.class);

            parsedHal.remove("_embedded");

            return mapper.writeValueAsString(parsedHal.get("_embedded"));

        } catch (IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    public String getValueList(String start, String separator, String end,
                               List<String> values) {

        StringBuilder sb = new StringBuilder();
        sb.append(start);
        boolean first = true;
        for (String value : values) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(value);
        }
        sb.append(end);
        return sb.toString();

    }

    public static HalEmbeddedMatcher embedded(AbstractJsonValueMatcher[] abstractJsonValueMatchers) {
        return new HalEmbeddedMatcher(abstractJsonValueMatchers);
    }
}