package com.github.osvaldopina.signedcontract.json;

import com.github.osvaldopina.signedcontract.Violation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by deinf.osvaldo on 28/07/2016.
 */
public class StringJsonPathPropertyValueTest {

    private StringJsonPathPropertyValue stringJsonPathPropertyValue;

    @Test
    public void noViolation() throws Exception {
        String document = "{ prop : \"value\" }";

        stringJsonPathPropertyValue = new StringJsonPathPropertyValue("$.prop", "value");

        assertEquals(0, stringJsonPathPropertyValue.enforce(document).size());

    }

    @Test
    public void valueViolation() throws Exception {
        String document = "{ prop : \"value\" }";

        stringJsonPathPropertyValue = new StringJsonPathPropertyValue("$.prop", "other-value");

        List<Violation> violations = stringJsonPathPropertyValue.enforce(document);

        assertEquals(1, violations.size());

        assertEquals("expected $.prop to be \"other-value\" but it was \"value\"", violations.get(0).toString());

    }

}