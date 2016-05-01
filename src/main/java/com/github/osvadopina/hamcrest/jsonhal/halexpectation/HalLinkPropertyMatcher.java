package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import org.hamcrest.Description;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HalLinkPropertyMatcher<T> extends HalLinkMatcher {

    private T value;

    private String propertyName;

    protected T currentValue;


    public HalLinkPropertyMatcher(T value, String propertyName) {
        this.value = value;
        this.propertyName = propertyName;
    }

    @Override
    protected boolean matchesSafely(HalLink halLink) {
        currentValue = getCurrentValue(halLink);
        return value.equals(currentValue);
    }

    protected T getCurrentValue(HalLink halLink) {
        throw new NotImplementedException();
    }


    @Override
    public void describeTo(Description description) {
        description
                .appendText("link ")
                .appendText(propertyName)
                .appendText(" to be ")
                .appendValue(value);
    }

    @Override
    protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
        mismatchDescription
                .appendText("link ")
                .appendText(propertyName)
                .appendText(" was ")
                .appendValue(currentValue);
    }

    public static HalLinkMatcher hasRel(String rel) {
        return new HalLinkPropertyMatcher<String>(rel, "rel") {

            protected String getCurrentValue(HalLink halLink) {
                return halLink.getRel();
            }
        };
    }

    public static HalLinkMatcher hasHref(String href) {
        return new HalLinkPropertyMatcher<String>(href, "href") {

            protected String getCurrentValue(HalLink halLink) {
                return halLink.getHRef();
            }
        };
    }

    public static HalLinkMatcher isTamplated() {
        return new HalLinkPropertyMatcher<Boolean>(true, "templated") {

            protected Boolean getCurrentValue(HalLink halLink) {
                return halLink.isTemplated();
            }
        };
    }

    public static HalLinkMatcher isNotTamplated() {
        return new HalLinkPropertyMatcher<Boolean>(false, "templated") {

            protected Boolean getCurrentValue(HalLink halLink) {
                return halLink.isTemplated();
            }
        };
    }

    public static HalLinkMatcher isHRelValidUri() {
        return new HalLinkMatcher() {


            @Override
            protected boolean matchesSafely(HalLink halLink) {

                try {
                    new URI(halLink.getHRef());
                    return true;
                } catch (URISyntaxException e) {
                    return false;
                }

            }

            @Override
            public void describeTo(Description description) {
                description
                        .appendText("link to have a valid href uri");
            }

            protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("link href is not a valid uri");
            }

        };
    }

    public static HalLinkMatcher isHRelValidUrl() {
        return new HalLinkMatcher() {

            @Override
            protected boolean matchesSafely(HalLink halLink) {
                try {
                    new URL(halLink.getHRef());
                    return true;
                } catch (MalformedURLException e) {
                    return false;
                }

            }

            @Override
            public void describeTo(Description description) {
                description
                        .appendText("link to have a valid href url");
            }

            protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("link href is not a valid url");
            }

        };
    }


}
