package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.Contract;
import com.github.osvaldopina.signedcontract.Violation;
import org.junit.Test;

import java.util.List;

import static com.github.osvaldopina.signedcontract.hal.SignedControlHalDsl.*;
import static org.junit.Assert.*;

/**
 * Created by deinf.osvaldo on 28/07/2016.
 */
public class SignedControlHalDslTest {

    private String hal;

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
    public void test() {
        Contract contract =
                halDocument(
                    resource(
                        is("$.prop1","property-1-value")
                    ),
                    links(
                        link("urn:irs:test")
                    )
                );

       List<Violation> violations = contract.enforce(hal);

        System.out.println(violations);
    }



}