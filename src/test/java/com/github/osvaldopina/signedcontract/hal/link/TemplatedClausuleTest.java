package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.Violation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TemplatedClausuleTest {

    TemplatedClausule templatedClausule;


    @Test
    public void templatedTrueJsonLinkTemplateTrue() {
        templatedClausule = new TemplatedClausule(true);

        String link = "{ \"rel\" : { \"templated\" : true } }";

        List<Violation> violations = templatedClausule.enforce(link);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void templatedFalseJsonLinkTemplateTrue() {
        templatedClausule = new TemplatedClausule(false);

        String link = "{ \"rel\" : { \"templated\" : true } }";

        List<Violation> violations = templatedClausule.enforce(link);

        assertEquals(1, violations.size());
        assertEquals("expecting link not to be templated. but it was templated", violations.get(0).toString());
    }

    @Test
    public void templatedTrueJsonLinkTemplateNotInformed() {
        templatedClausule = new TemplatedClausule(true);

        String link = "{ \"rel\" : {  } }";

        List<Violation> violations = templatedClausule.enforce(link);

        assertEquals(1, violations.size());
        assertEquals("expecting link to be templated. but it was not templated", violations.get(0).toString());
    }

    @Test
    public void templatedFalseJsonLinkTemplateNotInformed() {
        templatedClausule = new TemplatedClausule(false);

        String link = "{ \"rel\" : {  } }";

        List<Violation> violations = templatedClausule.enforce(link);

        assertTrue(violations.isEmpty());
    }
}