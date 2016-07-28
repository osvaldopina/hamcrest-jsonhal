package com.github.osvadopina.hamcrest.jsonhal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvadopina.hamcrest.jsonhal.jsonpath.AbstractJsonValueMatcher;
import com.github.osvadopina.hamcrest.jsonhal.jsonpath.JsonPathMatcher;
import com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableFindMatcher;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

public class HalResourceMatcher extends HalDocumentPartMatcher {

    private JsonPathMatcher[] jsonPathMatchers;

    private List<JsonPathMatcher> notMatched;

    public HalResourceMatcher(JsonPathMatcher... jsonPathMatchers) {
        this.jsonPathMatchers = jsonPathMatchers;
    }


    @Override
    public void describeTo(Description description) {
        if (!notMatched.isEmpty()) {
            description.appendText("for resource : ");
            description.appendList("(", ") and (", ")", notMatched);
        }
    }

    @Override
    protected boolean matchesSafely(String item) {
        String halResource = getOnlyResourceFromDocument(item);

        notMatched = new ArrayList<JsonPathMatcher>();

        for (JsonPathMatcher jsonPathMatcher: jsonPathMatchers) {
            if (!jsonPathMatcher.matches(halResource)) {
                notMatched.add(jsonPathMatcher);
            }
        }
        return notMatched.isEmpty();
    }

    @Override
    protected void describeMismatchSafely(String actual, Description mismatchDescription) {
        if (!notMatched.isEmpty()) {
            mismatchDescription.appendText("for resource : ");
            Description matcherDescription;
            List<String> descriptions = new ArrayList<String>();
            for (JsonPathMatcher jsonPathMatcher: notMatched) {
                matcherDescription = new StringDescription();
                jsonPathMatcher.describeMismatch(getOnlyResourceFromDocument(actual), matcherDescription);
                descriptions.add(matcherDescription.toString());
            }
            mismatchDescription.appendText(getValueList("(", ") and (", ")", descriptions));
        }
    }

    private String getOnlyResourceFromDocument(String item) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String,Object> parsedHal = mapper.readValue(item, Map.class);

            parsedHal.remove("_links");
            parsedHal.remove("_embedded");

            return mapper.writeValueAsString(parsedHal);

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

    public static HalResourceMatcher resource(JsonPathMatcher ...jsonPathMatchers) {
        return new HalResourceMatcher(jsonPathMatchers);
    }


}
