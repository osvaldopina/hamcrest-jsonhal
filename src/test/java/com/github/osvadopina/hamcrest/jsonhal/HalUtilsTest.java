package com.github.osvadopina.hamcrest.jsonhal;

import com.github.osvadopina.hamcrest.jsonhal.link.HalLink;
import com.github.osvadopina.hamcrest.jsonhal.link.HalUtils;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.osvadopina.hamcrest.jsonhal.HalDocumentMatcher.halDocument;
import static com.github.osvadopina.hamcrest.jsonhal.HalResourceMatcher.resource;
import static com.github.osvadopina.hamcrest.jsonhal.jsonpath.JsonPathMatcher.jsonPath;
import static com.github.osvadopina.hamcrest.jsonhal.jsonpath.JsonValueMatcher.is;
import static com.github.osvadopina.hamcrest.jsonhal.jsonpath.JsonValueTypeMatcher.isString;
import static com.github.osvadopina.hamcrest.jsonhal.link.HalLinkFindMatcher.haveLink;
import static com.github.osvadopina.hamcrest.jsonhal.link.HalLinkPropertyMatcher.*;
import static com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableFindMatcher.hasVariable;
import static com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class HalUtilsTest {

    private String hal;


    @Before
     public void setUp() {

        StringBuffer tmp = new StringBuffer();


//        tmp.append("{ \n");
//        tmp.append("    \"_links\": {\n");
//        tmp.append("        \"self\": {\n");
//        tmp.append("            \"href\": \"self-uri\",\n");
//        tmp.append("            \"templated\": \"true\"\n");
//        tmp.append("        },\n");
//        tmp.append("        \"link-rel\": {\n");
//        tmp.append("            \"href\": \"some-uri\"\n");
//        tmp.append("        },\n");
//        tmp.append("        \"link-rel-1\": {\n");
//        tmp.append("            \"href\": \"some-uri\"\n");
//        tmp.append("        }\n");
//        tmp.append("    }\n");
//        tmp.append("}");

        tmp.append("{ \n");
        tmp.append("    \"prop1\":  \"property-1-value\", \n");
        tmp.append("    \"_links\": {\n");
        tmp.append("        \"urn:irs:test\": {\n");
        tmp.append("            \"href\": \"http://localhost{/var1,var2*}{?var3}\",\n");
        tmp.append("            \"templated\": \"true\"\n");
        tmp.append("        },\n");
        tmp.append("        \"other\": {\n");
        tmp.append("            \"href\":[ \"hxxp:self-uri\", 125],\n");
        tmp.append("            \"templated\": \"true\"\n");
        tmp.append("        }\n");
        tmp.append("    },\n");
        tmp.append("    \"_embedded\": {\n");
        tmp.append("        \"emb1\": {\n");
        tmp.append("            \"prop-emb-1\": \"prop-emb-1-value\"\n");
        tmp.append("        },\n");
        tmp.append("        \"emb2\": {\n");
        tmp.append("            \"prop-emb-2\":\"prop-emb-2-value\"\n");
        tmp.append("        }\n");
        tmp.append("    }\n");
        tmp.append("}");

        hal = tmp.toString();
    }

    @Test
    public void getLinks() throws Exception {
        List<HalLink> links = HalUtils.getLinks(hal);


        Object o = JsonPath.parse(hal).read("$._links.other.href");
        System.out.println(o);


//        assertThat("string" , startsWith("ost"));
//        assertThat(links.get(0), hasRel("self-"));
//        assertThat(links.get(0), isTamplated());

//       assertThat(links.get(1), hasRel("link-rel"));
//        assertThat(links.get(1), isNotTamplated());



//        assertThat(links,
//                hasOnlyTheseLinks(
//                haveLink("self",
//                            hasRel("self")))
//        );

        assertThat(hal,
                halDocument(
                        resource(
                            jsonPath("$.prop1",
                                    isString(),
                                    is("property-1-value")
                            )
                        ),
                        HalEmbeddedsMatcher.embeddeds(


                        ),
                        HalLinksMatcher.links(
                            haveLink("urn:irs:test",
                                hasValidUriAsHRef(),
                                isTamplated(),
                                hasValidRel(),
                                href(
                                    hasVariable("var1",
                                        // hasQueryExpansion(),
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
                )
        );

//        assertThat(links,
//                  hasOnlyTheseLinks(
//                    haveLink("self",
//                            hasRel("self1"),
//                            hasRel("self2"),
//                            hasValidUriAsHRef(),
//                            isHRefValidUrl(),
//                            isTamplated(),
//                            href(
//                               hasVariable("var1",
//                                       UriVariableMatcher.hasPathExpansion(),
//                                       UriVariableMatcher.hasNotExplodedExpansion()
//                               ),
//                               hasVariable("var2",
//                                       UriVariableMatcher.hasPathExpansion(),
//                                       UriVariableMatcher.hasExplodedExpansion()
//                               ),
//                               hasVariable("var1",
//                                       UriVariableMatcher.hasQueryExpansion(),
//                                       UriVariableMatcher.hasNotExplodedExpansion()
//                               )
//                            )
//                    ),
//                    haveLink("link-rel",
//                            hasRel("link-rel"))
//        )
//        );
//
    }
}