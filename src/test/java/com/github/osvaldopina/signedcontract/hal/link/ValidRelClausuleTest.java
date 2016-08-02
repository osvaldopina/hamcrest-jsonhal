package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.Violation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ValidRelClausuleTest {

    ValidRelClausule validRelClausule;


    @Test
    public void relIsValidIANARel() {

        validRelClausule = new ValidRelClausule();

        String link = "{ \"next\" : { } }";

        List<Violation> violations = validRelClausule.enforce(link);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void relIsValidUri() {

        validRelClausule = new ValidRelClausule();

        String link = "{ \"http://localhost/uri\" : { } }";

        List<Violation> violations = validRelClausule.enforce(link);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void relIsNotValidIANAAndIsNotAbsoluteUri() {

        validRelClausule = new ValidRelClausule();

        String link = "{ \"not-ianna-and-not-absolute\" : { } }";

        List<Violation> violations = validRelClausule.enforce(link);

        assertEquals(1, violations.size());
        assertEquals("expecting link link to be a absolute uri or a valid IANA rel but it was not", violations.get(0).toString());
    }
    @Test

    public void relIsNotValidIANAAndIsNotValidUri() {

        validRelClausule = new ValidRelClausule();

        String link = "{ \"http://[url]\" : { } }";

        List<Violation> violations = validRelClausule.enforce(link);

        assertEquals(1, violations.size());
        assertEquals("expecting link link to be a absolute uri or a valid IANA rel but it was not", violations.get(0).toString());
    }

}