package com.github.osvadopina.hamcrest.jsonhal.jsonpath;

import org.hamcrest.Description;

import java.util.List;
import java.util.Map;

public abstract class JsonValueMatcher<T> extends AbstractJsonValueMatcher {

    protected T value;

    public JsonValueMatcher(T value) {
        this.value = value;
    }

    public static JsonValueMatcher<String> is(String value) {
        return new JsonValueMatcher<String>(value) {

            @Override
            protected boolean matchesSafely(Object item) {
                if (! value.equals(item)) {
                    return false;
                }
                else {
                    return true;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("be \"" + value + "\"");
            }

            @Override
            protected void describeMismatchSafely(Object actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("but it was ")
                        .appendValue(actual);
            }
        };
    }

}
