package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.AllOf.allOf;

public class HalLinksFindMatcher extends TypeSafeMatcher<List<HalLink>> {

    private final HalLinkMatcher[] matchers;
    private final String rel;
    private boolean found = false;
    private List<NotMatched> notMatchedLinks = new ArrayList<NotMatched>();

    public HalLinksFindMatcher(String rel, HalLinkMatcher... matchers) {
        this.rel = rel;
        this.matchers = matchers;
    }

    @Override
    protected boolean matchesSafely(List<HalLink> item) {
        notMatchedLinks.clear();
        for(HalLink link:item) {
            if (link.getRel().equals(rel)) {
                found = true;
                for(Matcher matcher:matchers) {
                    if (! matcher.matches(link)) {
                        notMatchedLinks.add(new NotMatched(link, matcher));
                    }
                }
            }
        }
        return found && notMatchedLinks.isEmpty();
    }

    @Override
    public void describeTo(Description description) {
        if (! found) {
          description.appendText("to find a link with rel ").appendValue(rel);
        }
        else {
            if (! notMatchedLinks.isEmpty()) {
                List<Matcher> descriptions = new ArrayList<Matcher>();
                description.appendText("for link with rel " + rel + ": ");
                Description matcherDescription;
                for (NotMatched notMatched : notMatchedLinks) {
                    descriptions.add(notMatched.getMatcher());
                }
                description.appendList("(", ") and (", ")", descriptions);
            }
        }
    }

    protected void describeMismatchSafely(List<HalLink> actual, Description mismatchDescription) {
        if (! found) {
            mismatchDescription.appendText("the hal links are ");
            List<String> linkList = new ArrayList<String>();
            for (HalLink halLink : actual) {
                linkList.add(halLink.getRel());
            }
            mismatchDescription.appendValueList("[", ",", "]", linkList);
        }
        else {
            if (! notMatchedLinks.isEmpty()) {
                List<Matcher> matchers = new ArrayList<Matcher>();
                mismatchDescription.appendText("for link with rel " + rel + ": ");
                Description matcherDescription;
                List<String> descriptions = new ArrayList<String>();
                for (NotMatched notMatched : notMatchedLinks) {
                    matcherDescription = new StringDescription();
                    notMatched.getMatcher().describeMismatch(notMatched.getLink(), matcherDescription);
                    descriptions.add(matcherDescription.toString());
                }
                mismatchDescription.appendText(getValueList("(", ") and (", ")", descriptions));
            }
        }
    }

    public String getRel() {
        return rel;
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

    public static HalLinksFindMatcher toHaveLink(String rel, HalLinkMatcher... matchers) {
        return new HalLinksFindMatcher(rel, matchers);
    }

    private static class NotMatched {
        private HalLink link;
        private Matcher<?> matcher;

        private NotMatched(HalLink link, Matcher<?> matcher) {
            this.link = link;
            this.matcher = matcher;
        }

        public HalLink getLink() {
            return link;
        }

        public Matcher<?> getMatcher() {
            return matcher;
        }
    }

}
