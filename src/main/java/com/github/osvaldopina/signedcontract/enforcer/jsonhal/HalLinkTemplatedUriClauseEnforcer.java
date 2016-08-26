package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;

import java.util.List;

public class HalLinkTemplatedUriClauseEnforcer extends BranchClauseEnforcer<JsonNode, UriTemplate> implements HalLinkPropertyClauseEnforcer {

    public HalLinkTemplatedUriClauseEnforcer(List<? extends ClauseEnforcer<UriTemplate>> subClauses) {
        super(subClauses);
    }

    @Override
    protected Navigator<JsonNode, UriTemplate> createNavigator() {
        return super.createNavigator();
    }
}
