package com.github.osvadopina.hamcrest.jsonhal;

import com.github.osvadopina.hamcrest.jsonhal.halexpectation.HalLink;
import com.github.osvadopina.hamcrest.jsonhal.halexpectation.HalUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.osvadopina.hamcrest.jsonhal.halexpectation.HalHasOnlyTheseLinksMatcher.hasOnlyTheseLinks;
import static com.github.osvadopina.hamcrest.jsonhal.halexpectation.HalLinkPropertyMatcher.hasRel;
import static com.github.osvadopina.hamcrest.jsonhal.halexpectation.HalLinkPropertyMatcher.isHRelValidUri;
import static com.github.osvadopina.hamcrest.jsonhal.halexpectation.HalLinkPropertyMatcher.isHRelValidUrl;
import static com.github.osvadopina.hamcrest.jsonhal.halexpectation.HalLinkPropertyMatcher.isTamplated;
import static com.github.osvadopina.hamcrest.jsonhal.halexpectation.HalLinksFindMatcher.toHaveLink;
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
        tmp.append("    \"_links\": {\n");
        tmp.append("        \"self\": {\n");
        tmp.append("            \"href\": \"self-uri\",\n");
        tmp.append("            \"templated\": \"true\"\n");
        tmp.append("        },\n");
        tmp.append("        \"other\": {\n");
        tmp.append("            \"href\": \"hxxp:self-uri\",\n");
        tmp.append("            \"templated\": \"true\"\n");
        tmp.append("        }\n");
        tmp.append("    }\n");
        tmp.append("}");

        hal = tmp.toString();

    }

    @Test
    public void getLinks() throws Exception {
        List<HalLink> links = HalUtils.getLinks(hal);


//        assertThat("string" , startsWith("ost"));
//        assertThat(links.get(0), hasRel("self-"));
//        assertThat(links.get(0), isTamplated());

//       assertThat(links.get(1), hasRel("link-rel"));
//        assertThat(links.get(1), isNotTamplated());



//        assertThat(links,
//                hasOnlyTheseLinks(
//                toHaveLink("self",
//                            hasRel("self")))
//        );



//        assertThat(links,
//                  hasOnlyTheseLinks(
//                    toHaveLink("self",
//                            hasRel("self1"),
//                            hasRel("self2"),
//                            isHRelValidUri(),
//                            isHRelValidUrl(),
//                            isTamplated()),
//                    toHaveLink("link-rel",
//                            hasRel("link-rel"))
//        )
//        );

    }
}