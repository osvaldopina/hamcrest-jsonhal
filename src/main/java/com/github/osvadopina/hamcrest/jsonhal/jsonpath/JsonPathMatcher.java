package com.github.osvadopina.hamcrest.jsonhal.jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

public class JsonPathMatcher extends TypeSafeMatcher<String> {

    private String jsonPath;

    private AbstractJsonValueMatcher[] abstractJsonValueMatchers;

    private List<AbstractJsonValueMatcher> notMatched;

    private Object value;

    public JsonPathMatcher(String jsonPath, AbstractJsonValueMatcher... abstractJsonValueMatchers) {
        this.jsonPath = jsonPath;
        this.abstractJsonValueMatchers = abstractJsonValueMatchers;
    }


    @Override
    protected boolean matchesSafely(String item) {

        value = JsonPath.parse(item).read(jsonPath);

        notMatched = new ArrayList<AbstractJsonValueMatcher>();

        for (AbstractJsonValueMatcher abstractJsonValueMatcher : abstractJsonValueMatchers) {
            if (!abstractJsonValueMatcher.matches(value)) {
                notMatched.add(abstractJsonValueMatcher);
            }
        }
        return notMatched.isEmpty();
    }

    @Override
    protected void describeMismatchSafely(String actual, Description mismatchDescription) {
        mismatchDescription
                .appendText("for jsonpath path : " + jsonPath + " ");
        Description matcherDescription;
        List<String> descriptions = new ArrayList<String>();
        for (AbstractJsonValueMatcher noMatch : notMatched) {
            matcherDescription = new StringDescription();
            noMatch.describeMismatch(JsonPath.parse(actual).read(jsonPath), matcherDescription);
            descriptions.add(matcherDescription.toString());
        }
        mismatchDescription.appendText(getValueList("(", ") and (", ")", descriptions));
    }


    @Override
    public void describeTo(Description description) {
        if (!notMatched.isEmpty()) {
            description.appendText("for jsonpath path : " + jsonPath + " ");
            description.appendList("(", ") and (", ")", notMatched);
        }

    }

    public static JsonPathMatcher jsonPath(String jsonPath, AbstractJsonValueMatcher... abstractJsonValueMatchers) {
        return new JsonPathMatcher(jsonPath, abstractJsonValueMatchers);
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
}
