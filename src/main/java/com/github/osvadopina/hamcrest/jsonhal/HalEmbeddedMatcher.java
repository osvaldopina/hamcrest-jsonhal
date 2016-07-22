package com.github.osvadopina.hamcrest.jsonhal;

import org.hamcrest.Description;

public class HalEmbeddedMatcher extends HalDocumentPartMatcher {


    @Override
    protected boolean matchesSafely(String item) {
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
