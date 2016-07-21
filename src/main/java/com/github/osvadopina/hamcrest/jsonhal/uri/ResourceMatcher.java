package com.github.osvadopina.hamcrest.jsonhal.uri;

import com.github.osvadopina.hamcrest.jsonhal.HalDocumentPartMatcher;
import org.hamcrest.Description;

public class ResourceMatcher extends HalDocumentPartMatcher {

    @Override
    protected boolean matchesSafely(String item) {
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
