package com.github.osvaldopina.signedcontract.hal;

import com.damnhandy.uri.template.UriTemplate;
import com.github.osvadopina.hamcrest.jsonhal.uri.UriTemplateVariable;
import com.github.osvaldopina.signedcontract.Contract;
import com.github.osvaldopina.signedcontract.hal.link.LinkClausule;
import com.github.osvaldopina.signedcontract.hal.link.BaseLinkPropertyClausule;
import com.github.osvaldopina.signedcontract.hal.link.UriTemplateClausule;
import com.github.osvaldopina.signedcontract.hal.link.uritemplate.UriTemplateVariableClausule;
import com.github.osvaldopina.signedcontract.json.JsonPathPropertyValue;
import com.github.osvaldopina.signedcontract.json.JsonPropertyList;
import com.github.osvaldopina.signedcontract.json.StringJsonPathPropertyValue;

import java.util.Arrays;

public class SignedControlHalDsl {

    public static Contract halDocument(HalClausule ... halClausules) {
        return new Contract(Arrays.asList(halClausules));
    }

    public static HalClausule resource(JsonPathPropertyValue ... jsonPathPropertyValues) {
        return new Resource (new JsonPropertyList(Arrays.asList(jsonPathPropertyValues)));
    }

    public static JsonPathPropertyValue is(String jsonPath, String value) {
        return  new StringJsonPathPropertyValue(jsonPath, value);
    }

    public static HalClausule links(LinkClausule... linkClausules) {
        return new Links(Arrays.asList(linkClausules));
    }

     public static LinkClausule link(String rel, BaseLinkPropertyClausule... linkProperties) {
        return new LinkClausule(rel, Arrays.asList(linkProperties));
    }

    public static UriTemplateClausule uriTemplate(UriTemplateVariableClausule... uriTemplateVariableClausules) {
        return new UriTemplateClausule(Arrays.asList(uriTemplateVariableClausules));
    }

    public static UriTemplateVariableClausule variable(String varName, UriTemplateVariableClausule ... uriTemplateVariableClausule) {
        return new UriTemplateVariableClausule(varName, Arrays.asList(uriTemplateVariableClausule));
    }

}
