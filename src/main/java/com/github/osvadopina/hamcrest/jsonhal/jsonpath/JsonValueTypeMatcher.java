package com.github.osvadopina.hamcrest.jsonhal.jsonpath;

import org.hamcrest.Description;

import java.util.List;
import java.util.Map;

public abstract class JsonValueTypeMatcher extends AbstractJsonValueMatcher {

    public static JsonValueTypeMatcher isBoolean() {
        return new JsonValueTypeMatcher() {

            @Override
            protected boolean matchesSafely(Object item) {
                return item instanceof Boolean;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("be a boolean");
            }

            @Override
            protected void describeMismatchSafely(Object actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("json value was ")
                        .appendValue(actual.getClass());
            }

        };
    }

    public static JsonValueTypeMatcher isNumber() {
        return new JsonValueTypeMatcher() {

            @Override
            protected boolean matchesSafely(Object item) {
                return item instanceof Number;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("be a number");
            }

            @Override
            protected void describeMismatchSafely(Object actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("json value was ")
                        .appendValue(actual.getClass());
            }

        };
    }

    public static JsonValueTypeMatcher isString() {
        return new JsonValueTypeMatcher() {

            @Override
            protected boolean matchesSafely(Object item) {
                return item instanceof String;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("be a string");
            }

            @Override
            protected void describeMismatchSafely(Object actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("json value was ")
                        .appendValue(actual.getClass());
            }

        };
    }

    public static JsonValueTypeMatcher isArray() {
        return new JsonValueTypeMatcher() {

            @Override
            protected boolean matchesSafely(Object item) {
                return item instanceof List;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("be a array");
            }

            @Override
            protected void describeMismatchSafely(Object actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("json value was ")
                        .appendValue(actual.getClass());
            }

        };
    }

    public static JsonValueTypeMatcher isObject() {
        return new JsonValueTypeMatcher() {

            @Override
            protected boolean matchesSafely(Object item) {
                return item instanceof Map;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("be a object");
            }

            @Override
            protected void describeMismatchSafely(Object actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("json value was ")
                        .appendValue(actual.getClass());
            }

        };
    }

}
