package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HalHasOnlyTheseLinksMatcher extends TypeSafeMatcher<List<HalLink>> {

    private final HalLinksFindMatcher[] findMatchers;
    private boolean found = false;
    private List<String> notFoundListRel;
    private List<HalLinksFindMatcher> notMatched;
    private List<String> allLinks;

    public HalHasOnlyTheseLinksMatcher(HalLinksFindMatcher... findMatchers) {
        this.findMatchers = findMatchers;
    }

    @Override
    protected boolean matchesSafely(List<HalLink> item) {
        notMatched = new ArrayList<HalLinksFindMatcher>();
        notFoundListRel = new ArrayList<String>();
        allLinks = new ArrayList<String>();
        for (HalLink link : item) {
            allLinks.add(link.getRel());
            boolean found = false;
            for (HalLinksFindMatcher halLinksFindMatcher : findMatchers) {
                if (halLinksFindMatcher.getRel().equals(link.getRel())) {
                    found = true;
                }
            }
            if (! found) {
                notFoundListRel.add(link.getRel());
            }
        }

        for(HalLinksFindMatcher findMatcher: findMatchers) {
            if (! findMatcher.matches(item)) {
              notMatched.add(findMatcher);
            }
        }

        return notFoundListRel.isEmpty() && notMatched.isEmpty();
    }
    @Override
    public void describeTo(Description description) {
        if (! notFoundListRel.isEmpty()) {
            description.appendText("to test all these links ");
            description.appendValueList("[", ",", "]", allLinks);
        }
        int i=0;
        for(HalLinksFindMatcher halLinksFindMatcher : findMatchers) {
            if (i!=0 || ! notFoundListRel.isEmpty()) {
                description.appendText(" and ");
            }
            halLinksFindMatcher.describeTo(description);
            i ++;
        }
    }

    protected void describeMismatchSafely(List<HalLink> actual, Description mismatchDescription) {
        if (! notFoundListRel.isEmpty()) {
            mismatchDescription.appendText("some links where not tested ");
            mismatchDescription.appendValueList("[", ",", "]", notFoundListRel);
        }
        int i=0;
        for(HalLinksFindMatcher halLinksFindMatcher : findMatchers) {
            if (i!=0 || ! notFoundListRel.isEmpty()) {
                mismatchDescription.appendText(" and ");
            }
            halLinksFindMatcher.describeMismatchSafely(actual, mismatchDescription);
            i ++;
        }
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

    public static HalHasOnlyTheseLinksMatcher hasOnlyTheseLinks(HalLinksFindMatcher... halLinksFindMatchers) {
        return new HalHasOnlyTheseLinksMatcher(halLinksFindMatchers);
    }

}
