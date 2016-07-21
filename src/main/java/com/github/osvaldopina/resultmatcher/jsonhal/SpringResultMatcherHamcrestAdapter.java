package com.github.osvaldopina.resultmatcher.jsonhal;

import com.github.osvadopina.hamcrest.jsonhal.link.HalLink;
import com.github.osvadopina.hamcrest.jsonhal.link.HalLinkFindMatcher;
import com.github.osvadopina.hamcrest.jsonhal.link.HalUtils;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

public class SpringResultMatcherHamcrestAdapter implements ResultMatcher{

    HalLinkFindMatcher[] halLinkFindMatchers;

    public SpringResultMatcherHamcrestAdapter(HalLinkFindMatcher ... halLinkFindMatchers) {
        this.halLinkFindMatchers = halLinkFindMatchers;
    }

    @Override
    public void match(MvcResult result) throws Exception {
        String halDocument = result.getResponse().getContentAsString();

        List<HalLink> links = HalUtils.getLinks(halDocument);
        for(HalLinkFindMatcher halLinkFindMatcher:halLinkFindMatchers) {
            if (! halLinkFindMatcher.matches(links)) {
                Description stringDescription = new StringDescription();
                halLinkFindMatcher.describeMismatch(links, stringDescription);
                System.out.println("desc:" + stringDescription);
            }
        }
    }

    public static ResultMatcher links(HalLinkFindMatcher ... halLinkFindMatchers) {
        return new SpringResultMatcherHamcrestAdapter(halLinkFindMatchers);
    }
}
