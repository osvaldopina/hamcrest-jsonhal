package com.github.osvadopina.hamcrest.jsonhal.jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

public class JsonPathMatcher extends TypeSafeMatcher<String> {

    private String jsonPath;

    private AbstractJsonValueMatcher[] abstractJsonValueMatchers;

    private List<AbstractJsonValueMatcher> notMatched;

    public JsonPathMatcher(AbstractJsonValueMatcher abstractJsonValueMatcher) {
        this.abstractJsonValueMatchers = abstractJsonValueMatchers;
    }


    @Override
    protected boolean matchesSafely(String item) {

        Object value = JsonPath.parse(item).read(jsonPath);

        notMatched = new ArrayList<AbstractJsonValueMatcher>();

        for (AbstractJsonValueMatcher abstractJsonValueMatcher : abstractJsonValueMatchers) {
            if (! abstractJsonValueMatcher.matches(value)) {
                notMatched.add(abstractJsonValueMatcher);
            }
        }
        return notMatched.isEmpty();
    }

    @Override
    public void describeTo(Description description) {
        if (!notMatched.isEmpty()) {
            description.appendText("for json path : " + jsonPath);
            description.appendList("(", ") and (", ")", notMatched);
        }

    }
}
