package com.github.osvadopina.hamcrest.jsonhal;

import com.github.osvadopina.hamcrest.jsonhal.jsonpath.JsonPathMatcher;
import com.github.osvadopina.hamcrest.jsonhal.link.HalLink;
import com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableFindMatcher;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
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

    @Override
    protected void describeMismatchSafely(String actual, Description mismatchDescription) {
        if (!notMatched.isEmpty()) {
            mismatchDescription.appendText("for a hal document : ");
            Description matcherDescription;
            List<String> descriptions = new ArrayList<String>();
            for (HalDocumentPartMatcher halDocumentPartMatcher: notMatched) {
                matcherDescription = new StringDescription();
                halDocumentPartMatcher.describeMismatch(actual, matcherDescription);
                descriptions.add(matcherDescription.toString());
            }
            mismatchDescription.appendText(getValueList("(", ") and (", ")", descriptions));
        }
    }


    public static HalDocumentMatcher halDocument(HalDocumentPartMatcher ...halDocumentPartMatchers) {
        return new HalDocumentMatcher(halDocumentPartMatchers);
    }

    public String getValueList(String start, String separator, String end,
                               List<String> values) {

        StringBuilder sb = new StringBuilder();
        sb.append(start);
        boolean first = true;
        for(String value: values) {
            if (first) {
                first = false;
            }
            else {
                sb.append(separator);
            }
            sb.append(value);
        }
        sb.append(end);
        return sb.toString();

    }


}
