package com.github.osvaldopina.resultmatcher.jsonhal;

public class SpringResultMatcherHamcrestAdapter {

        /*

        implements ResultMatcher{

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

    */
}
