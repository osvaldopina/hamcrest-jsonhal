package com.github.osvaldopina.signedcontract.hal.link;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.BranchClause;

import java.io.IOException;
import java.util.*;

public class LinkClause extends BranchClause {

    private String rel;

    public LinkClause(String rel, List<LinkPropertyClause> subClausules) {
        super(subClausules);
        this.rel = rel;
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
            Map<String, Object> link = new HashMap<String, Object>();
            if (parsedHal.get(rel) == null) {
                return "{}";
            }
            else {
                link.put(rel, parsedHal.get(rel));
                return mapper.writeValueAsString(link);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void enforceClause(String document) {

        Map<String,Object> links = parseJson(document);
        if (links.get(rel) == null) {
            addHaltViolation("could not find link with rel " + rel);
        }
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
