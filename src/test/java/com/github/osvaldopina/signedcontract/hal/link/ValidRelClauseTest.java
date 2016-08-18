package com.github.osvaldopina.signedcontract.hal.link;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidRelClauseTest {

    ValidRelClause validRelClausule;


    @Test
    public void relIsValidIANARel() {

        validRelClausule = new ValidRelClause();

        String link = "{ \"next\" : { } }";

        validRelClausule.enforce(link);

        assertTrue(validRelClausule.getViolations().isEmpty());
    }

    @Test
    public void relIsValidUri() {

        validRelClausule = new ValidRelClause();

        String link = "{ \"http://localhost/uri\" : { } }";

        validRelClausule.enforce(link);

        assertTrue(validRelClausule.getViolations().isEmpty());
    }

    @Test
    public void relIsNotValidIANAAndIsNotAbsoluteUri() {

        validRelClausule = new ValidRelClause();

        String link = "{ \"not-ianna-and-not-absolute\" : { } }";

        validRelClausule.enforce(link);

        assertEquals(1, validRelClausule.getViolations().size());
        assertEquals("Expecting link rel to be a absolute uri or a valid IANA rel.", validRelClausule.getViolations().get(0).toString());
    }
    @Test

    public void relIsNotValidIANAAndIsNotValidUri() {

        validRelClausule = new ValidRelClause();

        String link = "{ \"http://[url]\" : { } }";

        validRelClausule.enforce(link);

        assertEquals(1, validRelClausule.getViolations().size());
        assertEquals("Expecting link rel to be a absolute uri or a valid IANA rel.", validRelClausule.getViolations().get(0).toString());
    }

}