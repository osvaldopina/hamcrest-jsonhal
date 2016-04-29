package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HalLinkPropertyMatcher extends HalLinkMatcher {

    private String value;

    private HalLinkProperty halLinkProperty;

    public HalLinkPropertyMatcher(String value, HalLinkProperty halLinkProperty) {
        this.value = value;
        this.halLinkProperty = halLinkProperty;
    }

    @Override
    protected boolean matchesSafely(HalLink item) {
        String propValue = item.getPropertyValue(halLinkProperty);

        if ("false".equals(value) && halLinkProperty == HalLinkProperty.TEMPLATED) {
           return (propValue == null || "false".equals(propValue));
        }
        else {
            return (propValue != null && propValue.equals(value));
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("link "  + halLinkProperty.getPropertyName() + " to be ").appendValue(value);
    }

    protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
        mismatchDescription.appendText("link "+halLinkProperty.getPropertyName() + " was ").appendValue(actual.getPropertyValue(halLinkProperty));
    }

    public static HalLinkMatcher hasRel(String rel) {
        return new HalLinkPropertyMatcher(rel, HalLinkProperty.REL);
    }

    public static HalLinkMatcher hasHref(String href) {
        return new HalLinkPropertyMatcher(href, HalLinkProperty.HREF);
    }

    public static HalLinkMatcher isTamplated() {
        return new HalLinkPropertyMatcher("true", HalLinkProperty.TEMPLATED);
    }

    public static HalLinkMatcher isNotTamplated() {
        return new HalLinkPropertyMatcher("false", HalLinkProperty.TEMPLATED);
    }



}
