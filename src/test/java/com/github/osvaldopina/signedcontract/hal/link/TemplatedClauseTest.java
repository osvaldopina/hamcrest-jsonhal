package com.github.osvaldopina.signedcontract.hal.link;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemplatedClauseTest {

    TemplatedClause templatedClausule;


    @Test
    public void templatedTrueJsonLinkTemplateTrue() {
        templatedClausule = new TemplatedClause(true);

        String link = "{ \"rel\" : { \"templated\" : true } }";

        templatedClausule.enforce(link);

        assertTrue(templatedClausule.getViolations().isEmpty());
    }

    @Test
    public void templatedFalseJsonLinkTemplateTrue() {
        templatedClausule = new TemplatedClause(false);

        String link = "{ \"rel\" : { \"templated\" : true } }";

        templatedClausule.enforce(link);

        assertEquals(1, templatedClausule.getViolations().size());
        assertEquals("expecting link not to be templated. but it was templated", templatedClausule.getViolations().get(0).toString());
    }

    @Test
    public void templatedTrueJsonLinkTemplateNotInformed() {
        templatedClausule = new TemplatedClause(true);

        String link = "{ \"rel\" : {  } }";

        templatedClausule.enforce(link);

        assertEquals(1, templatedClausule.getViolations().size());
        assertEquals("expecting link to be templated. but it was not templated", templatedClausule.getViolations().get(0).toString());
    }

    @Test
    public void templatedFalseJsonLinkTemplateNotInformed() {
        templatedClausule = new TemplatedClause(false);

        String link = "{ \"rel\" : {  } }";

        templatedClausule.enforce(link);

        assertTrue(templatedClausule.getViolations().isEmpty());
    }
}