package com.github.osvaldopina.signedcontract.enforcer.jsonpath;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Error;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonPathStringValueClauseTest {

    private JsonPathStringValueClause jsonPathStringValueClause;

    @Test
    public void validateString() throws Exception {

        jsonPathStringValueClause = new JsonPathStringValueClause("$.prop1", "prop1-value");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : \"prop1-value\" }");

        assertTrue(jsonPathStringValueClause.enforce(root).isEmpty());

    }

    @Test
    public void validateNonString() throws Exception {

        jsonPathStringValueClause = new JsonPathStringValueClause("$.prop1", "prop1-value");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : 124 }");

        List<Error> errors = jsonPathStringValueClause.enforce(root);

        assertEquals(1, errors.size());
        assertEquals("Was expecting \"prop1-value\" for json path $.prop1 to be a String but it was Integer", errors.get(0).toString());

    }

    @Test
    public void validatePropertyNotFound() throws Exception {

        jsonPathStringValueClause = new JsonPathStringValueClause("$.prop1", "prop1-value");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop2\" : 124 }");

        List<Error> errors = jsonPathStringValueClause.enforce(root);

        assertEquals(1, errors.size());
        assertEquals("Was expecting \"prop1-value\" for json path $.prop1 but the path was not found in json document", errors.get(0).toString());

    }
}