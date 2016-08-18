package com.github.osvadopina.hamcrest.jsonhal.link;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class HalLinkClauseFindMatcherTest {

    private HalLinkMatcher alwaysTrueHalLinkMatcher = new HalLinkMatcher() {
        @Override
        public void describeTo(Description description) {
            throw new IllegalStateException("must not be called!");
        }

        @Override
        protected boolean matchesSafely(HalLink item) {
            return true;
        }

    };

    private HalLinkMatcher alwaysFalseHalLinkMatcher = new HalLinkMatcher() {
        @Override
        protected boolean matchesSafely(HalLink item) {
            return false;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("error message");
        }

        @Override
        protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
            mismatchDescription.appendText("mismatch error message");
        }
    };

    private List<HalLink> links;

    @Before
    public void setUp() {
        links = new ArrayList<HalLink>();
        links.add(new HalLink("link1", new HashMap<String, Object>()));
        links.add(new HalLink("link2", new HashMap<String, Object>()));
    }

    /**
     * In a list of hal links tries to find a link that EXISTS in the list. The inner link
     * matchers always returns true therefor the assertion DOES NOT RAISE an exception.
     */
    @Test
    public void findMatcherLinkFoundInnerMatcherTrue() {

        assertThat(links, new HalLinkFindMatcher("link1", alwaysTrueHalLinkMatcher));

    }

    /**
     * In a list of hal links tries to find a link that DOES NOT EXISTS in the list. The inner link
     * matchers always returns true therefor the assertion RAISES an exception indicating that the link
     * was not found.
     */
    @Test
    public void findMatcherLinkNotFoundInnerMatcherTrue() {

        try {

            assertThat(links, new HalLinkFindMatcher("link-not-found", alwaysTrueHalLinkMatcher));

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to find a link with rel \"link-not-found\""),
                            containsString("the hal links are [\"link1\",\"link2\"]")
                    )
            );
        }

    }

    /**
     * In a list of hal links tries to find a link that DOES NOT EXISTS in the list. The inner link
     * matchers always returns false therefor the assertion RAISES an exception indicating that the innner
     * matcher was violated
     */
    @Test
    public void findMatcherLinkFoundInnerMatcherFalse() {

        try {

            assertThat(links, new HalLinkFindMatcher("link1", alwaysFalseHalLinkMatcher));

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("for link with rel link1: (error message)"),
                            containsString("for link with rel link1: (mismatch error message)")
                    )
            );
        }

    }

    /**
     * In a list of hal links tries to find a link that EXISTS in the list. The inner link
     * matchers always returns false therefor the assertion RAISES an exception indicating that the innner
     * matchers where violated
     */
    @Test
    public void findMatcherLinkFoundTwoInnerMatcherFalse() {

        try {

            assertThat(links, new HalLinkFindMatcher("link1",alwaysFalseHalLinkMatcher, alwaysFalseHalLinkMatcher));

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                        containsString("for link with rel link1: (error message) and (error message)"),
                        containsString("for link with rel link1: (mismatch error message) and (mismatch error message")
                    )
            );
        }

    }

    /**
     * In a list of hal links tries to find a link that DOES NOT EXISTS in the list. The inner link
     * matchers always returns false therefor the assertion RAISES an exception indicating that the link was
     * not found and but the inner matcher, although false, is not even tested
     */
    @Test
    public void findMatcherLinkNotFoundInnerMatcherFalse() {

        try {

            assertThat(links,
                    new HalLinkFindMatcher("link-not-found",alwaysFalseHalLinkMatcher));

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                         containsString("to find a link with rel \"link-not-found\""),
                         containsString("the hal links are [\"link1\",\"link2\"]")
                    )
            );
        }

    }
}