package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;

/**
 * Created by deinf.osvaldo on 26/08/2016.
 */
public class HalTemplatedLinkUriTemplateNavigator implements Navigator<JsonNode, UriTemplate> {
    @Override
    public UriTemplate navigate(JsonNode document) {
        return UriTemplate.fromTemplate(document.get("href").asText());
    }
}
