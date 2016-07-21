package com.github.osvadopina.hamcrest.jsonhal;

import com.github.osvadopina.hamcrest.jsonhal.link.HalLink;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HalDocumentMatcher extends TypeSafeMatcher<String> {

    public HalDocumentMatcher(HalDocumentPartMatcher halDocumentPartMatcher) {

    }


    @Override
    protected boolean matchesSafely(String item) {
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
