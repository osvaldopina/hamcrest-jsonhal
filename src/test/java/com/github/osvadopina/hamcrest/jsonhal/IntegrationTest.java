package com.github.osvadopina.hamcrest.jsonhal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.github.osvadopina.hamcrest.jsonhal.link.HalLinkFindMatcher.haveLink;
import static com.github.osvadopina.hamcrest.jsonhal.link.HalLinkPropertyMatcher.*;
import static com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableFindMatcher.hasVariable;
import static com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableMatcher.*;
import static com.github.osvaldopina.resultmatcher.jsonhal.SpringResultMatcherHamcrestAdapter.links;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = RestControllerTest.class)

public class IntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test() throws Exception {

        mockMvc.perform(get("/hal"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        links(
                                haveLink("self",
                                        hasValidUriAsHRef(),
                                        isTamplated(),
                                        hasValidRel(),
                                        href(
                                                hasVariable("var1",
                                                        hasPathExpansion(),
                                                        hasNotExplodedExpansion()
                                                ),
                                                hasVariable("var2",
                                                        hasPathExpansion(),
                                                        hasExplodedExpansion()
                                                ),
                                                hasVariable("var3",
                                                        hasQueryExpansion(),
                                                        hasNotExplodedExpansion()
                                                )
                                        )
                                )
                        )
                );
    }
}