package com.github.osvadopina.hamcrest.jsonhal;

import com.github.osvadopina.hamcrest.jsonhal.link.HalLink;
import com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableFindMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

public class HalDocumentMatcher extends TypeSafeMatcher<String> {

    private HalDocumentPartMatcher[] halDocumentPartMatchers;

    private List<HalDocumentPartMatcher> notMatched;

    public HalDocumentMatcher(HalDocumentPartMatcher ... halDocumentPartMatchers) {
        this.halDocumentPartMatchers = halDocumentPartMatchers;
    }


    @Override
    protected boolean matchesSafely(String item) {
        notMatched = new ArrayList<HalDocumentPartMatcher>();

        for (HalDocumentPartMatcher halDocumentPartMatcher:halDocumentPartMatchers) {
            if (!halDocumentPartMatcher.matches(item)) {
                notMatched.add(halDocumentPartMatcher);
            }
        }
        return notMatched.isEmpty();
    }

    @Override
    public void describeTo(Description description) {
        if (!notMatched.isEmpty()) {
            description.appendText("for a hal document : ");
            description.appendList("(", ") and (", ")", notMatched);
        }

    }

}
