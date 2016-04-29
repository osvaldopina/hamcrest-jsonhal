package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HalLinkMatcher extends TypeSafeMatcher<HalLink> {

    private String value;

    private HalLinkProperty halLinkProperty;

    public HalLinkMatcher(String value, HalLinkProperty halLinkProperty) {
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
        return new HalLinkMatcher(rel, HalLinkProperty.REL);
    }

    public static HalLinkMatcher hasHref(String href) {
        return new HalLinkMatcher(href, HalLinkProperty.HREF);
    }

    public static HalLinkMatcher isTamplated() {
        return new HalLinkMatcher("true", HalLinkProperty.TEMPLATED);
    }

    public static HalLinkMatcher isNotTamplated() {
        return new HalLinkMatcher("false", HalLinkProperty.TEMPLATED);
    }



}
