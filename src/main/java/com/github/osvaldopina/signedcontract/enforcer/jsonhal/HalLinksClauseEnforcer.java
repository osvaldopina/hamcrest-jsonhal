package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Error;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.JsonStayPutNavigator;

import java.util.Collections;
import java.util.List;

public class HalLinksClauseEnforcer extends  HalDocumentPartClauseEnforcer {


    public HalLinksClauseEnforcer(List<? extends ClauseEnforcer<JsonNode>> subClauses) {
        super(subClauses);
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonStayPutNavigator();
    }
}
