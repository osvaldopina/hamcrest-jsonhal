package com.github.osvadopina.hamcrest.jsonhal.link;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class HalLinkClausePropertyMatcherTest {

    private HalLink halLink;

    @Test
    public void relError() {
        halLink = new HalLink("rel", new HashMap<String, Object>());

        try {
            assertThat(halLink, HalLinkPropertyMatcher.hasRel("other"));
        }
        catch(AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                        containsString("Expected: link rel to be \"other\""),
                        containsString("but: link rel was \"rel\"")
                    )
            );
        }
    }

    @Test
    public void rel() {
        halLink = new HalLink("rel", new HashMap<String, Object>());

        assertThat(halLink, HalLinkPropertyMatcher.hasRel("rel"));
    }

    @Test
    public void hrefError() {

        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("href", "value");

        halLink = new HalLink("rel",linkProperties);

        try {
            assertThat(halLink, HalLinkPropertyMatcher.hasHref("value"));
        }
        catch(AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("Expected: link href to be \"other\""),
                            containsString("but: link href was \"rel\"")
                    )
            );
        }
    }

    @Test
    public void href() {
        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("href", "value");

        halLink = new HalLink("rel",linkProperties);

        assertThat(halLink, HalLinkPropertyMatcher.hasHref("value"));
    }

    @Test
    public void templatedError() {

        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("templated", "false");

        halLink = new HalLink("rel",linkProperties);

        try {
            assertThat(halLink, HalLinkPropertyMatcher.isTamplated());
        }
        catch(AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("Expected: link to be templated"),
                            containsString("but: link is not templated")
                    )
            );
        }
    }

    @Test
    public void templated() {
        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("templated", "true");

        halLink = new HalLink("rel",linkProperties);

        assertThat(halLink, HalLinkPropertyMatcher.isTamplated());
    }

    @Test
    public void notTemplatedError() {

        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("templated", "true");

        halLink = new HalLink("rel",linkProperties);

        try {
            assertThat(halLink, HalLinkPropertyMatcher.isNotTamplated());
        }
        catch(AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("Expected: link not to be templated"),
                            containsString("but: link is templated")
                    )
            );
        }
    }

    @Test
    public void notTemplated() {
        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("templated", "false");

        halLink = new HalLink("rel",linkProperties);

        assertThat(halLink, HalLinkPropertyMatcher.isNotTamplated());
    }

    @Test
    public void hRelValidUriError() {

        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("href", "\n");

        halLink = new HalLink("rel",linkProperties);

        try {
            assertThat(halLink, HalLinkPropertyMatcher.hasValidUriAsHRef());
        }
        catch(AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("Expected: link to have a valid href uri"),
                            containsString("but: link href is not a valid uri")
                    )
            );
        }
    }

    @Test
    public void hRelValidUri() {
        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("href", "uri");

        halLink = new HalLink("rel",linkProperties);

        assertThat(halLink, HalLinkPropertyMatcher.hasValidUriAsHRef());
    }

    @Test
    public void hRelValidUrlError() {

        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("href", "\n");

        halLink = new HalLink("rel",linkProperties);

        try {
            assertThat(halLink, HalLinkPropertyMatcher.isHRefValidUrl());
        }
        catch(AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("Expected: link to have a valid href url"),
                            containsString("but: link href is not a valid url")
                    )
            );
        }
    }

    @Test
    public void hRelValidUrl() {
        Map<String,Object > linkProperties = new HashMap<String, Object>();
        linkProperties.put("href", "http://localhost:8080");

        halLink = new HalLink("rel",linkProperties);

        assertThat(halLink, HalLinkPropertyMatcher.isHRefValidUrl());
    }

}