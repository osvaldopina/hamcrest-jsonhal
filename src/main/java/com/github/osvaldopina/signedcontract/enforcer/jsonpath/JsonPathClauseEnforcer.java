package com.github.osvaldopina.signedcontract.enforcer.jsonpath;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Error;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.util.Collections;
import java.util.List;

public class JsonPathClauseEnforcer extends LeafClauseEnforcer<JsonNode> {

    private String path;

    public JsonPathClauseEnforcer(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    protected final List<Error> enforceClause(JsonNode documentClause) {

        try {
            Object value = JsonPath.read(documentClause.toString(), path);

            return validate(value);

        }
        catch(PathNotFoundException e) {
            return validate(null);
        }
    }

    protected List<Error> validate(Object actual) {
        return Collections.emptyList();
    }




}
