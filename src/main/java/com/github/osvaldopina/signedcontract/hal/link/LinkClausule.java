package com.github.osvaldopina.signedcontract.hal.link;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.BranchClausule;
import com.github.osvaldopina.signedcontract.Violation;

import java.io.IOException;
import java.util.*;

public class LinkClausule extends BranchClausule {

    private String rel;

    public LinkClausule(String rel, List<BaseLinkPropertyClausule> subClausules) {
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
            return mapper.writeValueAsString( new HashMap<String, Object>().put(rel, parsedHal.get(rel)));
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
