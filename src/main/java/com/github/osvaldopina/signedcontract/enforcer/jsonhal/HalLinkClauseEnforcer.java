package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Error;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.JsonPropertyNavigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HalLinkClauseEnforcer extends BranchClauseEnforcer<JsonNode, JsonNode> {

    private String rel;

    public HalLinkClauseEnforcer(String rel, List<ClauseEnforcer<JsonNode>> subClauses) {
        super(subClauses);
        this.rel = rel;
    }


    protected List<Error> enforceClause(JsonNode documentClause) {

        if (!documentClause.has(rel)) {
            return Arrays.asList(new Error(
                    new StringBuilder()
                            .append("Was expecting to find a hal link with rel ")
                            .append(rel)
                            .append(" but it was not found!")
                            .toString()));
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonPropertyNavigator(rel);
    }
}
