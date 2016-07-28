package com.github.osvadopina.hamcrest.jsonhal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvadopina.hamcrest.jsonhal.link.HalLink;
import com.github.osvadopina.hamcrest.jsonhal.link.HalLinkFindMatcher;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HalLinksMatcher extends HalDocumentPartMatcher {


    private HalLinkFindMatcher[] halLinkFindMatchers;

    private List<HalLinkFindMatcher> notMatched;

    public HalLinksMatcher(HalLinkFindMatcher[] halLinkFindMatchers) {
        this.halLinkFindMatchers = halLinkFindMatchers;
    }


    @Override
    public void describeTo(Description description) {
        if (!notMatched.isEmpty()) {
            description.appendText("for links : ");
            description.appendList("(", ") and (", ")", notMatched);
        }
    }

    @Override
    protected boolean matchesSafely(String item) {
        List<HalLink> halLinks = getLinksFromDocument(item);

        notMatched = new ArrayList<HalLinkFindMatcher>();

        for (HalLinkFindMatcher linkFindMatcher : halLinkFindMatchers) {
            if (!linkFindMatcher.matches(halLinks)) {
                notMatched.add(linkFindMatcher);
            }
        }
        return notMatched.isEmpty();
    }

    @Override
    protected void describeMismatchSafely(String actual, Description mismatchDescription) {
        if (!notMatched.isEmpty()) {
            mismatchDescription.appendText("for links : ");
            Description matcherDescription;
            List<String> descriptions = new ArrayList<String>();
            for (HalLinkFindMatcher halLinkFindMatcher : notMatched) {
                matcherDescription = new StringDescription();
                halLinkFindMatcher.describeMismatch(getLinksFromDocument(actual), matcherDescription);
                descriptions.add(matcherDescription.toString());
            }
            mismatchDescription.appendText(getValueList("(", ") and (", ")", descriptions));
        }
    }

    private List<HalLink> getLinksFromDocument(String item) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, Object> parsedHal = mapper.readValue(item, Map.class);

            Object links = parsedHal.get("_links");

            Map<String, Map<String, Object>> halLinks = (Map<String, Map<String, Object>>) links;

            List<HalLink> result = new ArrayList<HalLink>();

            for (String link : halLinks.keySet()) {
                result.add(new HalLink(link, halLinks.get(link)));

            }
            return result;

        } catch (IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    public String getValueList(String start, String separator, String end,
                               List<String> values) {

        StringBuilder sb = new StringBuilder();
        sb.append(start);
        boolean first = true;
        for (String value : values) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(value);
        }
        sb.append(end);
        return sb.toString();

    }

    public static HalLinksMatcher links(HalLinkFindMatcher ...halLinkFindMatchers) {
        return new HalLinksMatcher(halLinkFindMatchers);
    }

}
