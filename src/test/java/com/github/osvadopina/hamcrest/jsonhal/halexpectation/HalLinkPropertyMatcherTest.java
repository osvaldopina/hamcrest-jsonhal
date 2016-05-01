package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by osvaldopina on 5/1/16.
 */
public class HalLinkPropertyMatcherTest {

    private HalLink halLink;

    @Test
    public void rel() {
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

}