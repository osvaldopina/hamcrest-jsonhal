package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvadopina.hamcrest.jsonhal.HalDocumentMatcher;
import com.github.osvaldopina.signedcontract.enforcer.Error;
import com.github.osvaldopina.signedcontract.enforcer.jsonpath.JsonPathStringValueClause;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HalDocumentClauseEnforcerTest {

    private HalDocumentClauseEnforcer halDocumentClauseEnforcer;

    @Test
    public void enforceHalDocument() throws Exception {

        halDocumentClauseEnforcer = new HalDocumentClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{  \"_links\" : {}  }");

        assertTrue(halDocumentClauseEnforcer.enforce(root).isEmpty());
    }

    @Test
    public void enforceNonHalDocument() throws Exception {

        halDocumentClauseEnforcer = new HalDocumentClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{  \"_no-links\" : {}  }");

        List<Error> erros = halDocumentClauseEnforcer.enforce(root);

        assertEquals(1, erros.size());
        assertEquals("Was expecting a hal document but \"_links\" property was not found!", erros.get(0));
    }

}