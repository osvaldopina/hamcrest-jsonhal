package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Error;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.JsonStayPutNavigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HalDocumentClauseEnforcer extends BranchClauseEnforcer<JsonNode, JsonNode> {

    public HalDocumentClauseEnforcer(List<? extends ClauseEnforcer<JsonNode>> subClauses) {
        super(subClauses);
    }

    @Override
    protected List<Error> enforceClause(JsonNode documentClause) {

        if ((!documentClause.isObject()) || (!documentClause.has("_links"))) {
            return Arrays.asList(new Error("Was expecting a hal document but \"_links\" property was not found!"));
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonStayPutNavigator();
    }
}
