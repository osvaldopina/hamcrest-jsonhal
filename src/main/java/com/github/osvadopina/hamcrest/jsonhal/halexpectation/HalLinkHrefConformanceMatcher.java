package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.net.URI;
import java.net.URISyntaxException;

public class HalLinkHrefConformanceMatcher extends HalLinkMatcher {

    private String name;


    public HalLinkHrefConformanceMatcher() {
    }

    @Override
    protected boolean matchesSafely(HalLink item) {

        name = item.getPropertyValue(HalLinkProperty.REL);
        try {
            new URI(item.getPropertyValue(HalLinkProperty.HREF));
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("link "  + name + " to be a valid uri");
    }

    public static HalLinkMatcher isHRelValidUri() {
        return new HalLinkHrefConformanceMatcher();
    }



}
