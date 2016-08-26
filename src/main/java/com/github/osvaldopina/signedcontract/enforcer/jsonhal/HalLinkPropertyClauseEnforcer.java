package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Error;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;

import java.util.List;

public interface HalLinkPropertyClauseEnforcer extends ClauseEnforcer<JsonNode> {

}
