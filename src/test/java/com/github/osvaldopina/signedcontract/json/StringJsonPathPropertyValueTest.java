package com.github.osvaldopina.signedcontract.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringJsonPathPropertyValueTest {

    private StringJsonPathPropertyValue stringJsonPathPropertyValue;

    @Test
    public void noViolation() throws Exception {
        String document = "{ prop : \"value\" }";

        stringJsonPathPropertyValue = new StringJsonPathPropertyValue("$.prop", "value");

        stringJsonPathPropertyValue.enforce(document);

        assertEquals(0, stringJsonPathPropertyValue.getViolations().size());

    }

    @Test
    public void valueViolation() throws Exception {
        String document = "{ prop : \"value\" }";

        stringJsonPathPropertyValue = new StringJsonPathPropertyValue("$.prop", "other-value");

        stringJsonPathPropertyValue.enforce(document);

        assertEquals(1, stringJsonPathPropertyValue.getViolations().size());

        assertEquals("expected $.prop to be \"other-value\" but it was \"value\"", stringJsonPathPropertyValue.getViolations().get(0).toString());

    }

}