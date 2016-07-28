package com.github.osvaldopina.signedcontract.hal.link;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.BranchClausule;
import com.github.osvaldopina.signedcontract.Violation;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Link extends BranchClausule {

    private String rel;

    public Link(String rel, List<LinkProperty> subClausules) {
        super(subClausules);
    }

    public String getRel() {
        return rel;
    }

    @Override
    public String dismemberDocument(String document) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> parsedHal = null;
        try {
            parsedHal = mapper.readValue(document, Map.class);
            return mapper.writeValueAsString(parsedHal.get(rel));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Violation> enforceClause(String document) {

        Map<String,Object> links = parseJson(document);
        if (links.get(rel) == null) {
            return Arrays.asList((Violation) new LinkNotFoundViolation(rel, links.keySet()));
        }
        return Collections.emptyList();
    }

    private Map<String,Object> parseJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
