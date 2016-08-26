package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.JsonStayPutNavigator;

import java.util.List;

public class HalDocumentPartClauseEnforcer extends BranchClauseEnforcer<JsonNode, JsonNode> {
    public HalDocumentPartClauseEnforcer(List<? extends ClauseEnforcer<JsonNode>> subClauses) {
        super(subClauses);
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonStayPutNavigator();
    }
}
