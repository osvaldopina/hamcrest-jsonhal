package com.github.osvaldopina.resultmatcher.jsonhal;

import com.github.osvadopina.hamcrest.jsonhal.link.HalLink;
import com.github.osvadopina.hamcrest.jsonhal.link.HalLinkFindMatcher;
import com.github.osvadopina.hamcrest.jsonhal.link.HalUtils;
import org.hamcrest.Description;
import org.hamcrest.MatcherAssert;
import org.hamcrest.StringDescription;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

public class SpringResultMatcherHamcrestAdapter implements ResultMatcher{

    HalLinkFindMatcher halLinkFindMatcher;

    public SpringResultMatcherHamcrestAdapter(HalLinkFindMatcher  halLinkFindMatcher) {
        this.halLinkFindMatcher = halLinkFindMatcher;
    }

    @Override
    public void match(MvcResult result) throws Exception {
        String halDocument = result.getResponse().getContentAsString();

        List<HalLink> links = HalUtils.getLinks(halDocument);

        MatcherAssert.assertThat(links,halLinkFindMatcher);

    }

    public static ResultMatcher links(HalLinkFindMatcher halLinkFindMatcher) {
        return new SpringResultMatcherHamcrestAdapter(halLinkFindMatcher);
    }
}
