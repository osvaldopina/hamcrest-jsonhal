package com.github.osvaldopina.signedcontract.hal;

import com.damnhandy.uri.template.impl.Modifier;
import com.damnhandy.uri.template.impl.Operator;
import com.github.osvaldopina.signedcontract.Contract;
import com.github.osvaldopina.signedcontract.hal.link.LinkClause;
import com.github.osvaldopina.signedcontract.hal.link.LinkPropertyClause;
import com.github.osvaldopina.signedcontract.hal.link.UriTemplateClause;
import com.github.osvaldopina.signedcontract.uritemplate.UriTemplateVariableClause;
import com.github.osvaldopina.signedcontract.uritemplate.UriTemplateVariableFindClause;
import com.github.osvaldopina.signedcontract.uritemplate.UriTemplateVariableModifierClause;
import com.github.osvaldopina.signedcontract.uritemplate.UriTemplateVariableOperatorClause;
import com.github.osvaldopina.signedcontract.json.JsonPathPropertyValue;
import com.github.osvaldopina.signedcontract.json.JsonPropertyList;
import com.github.osvaldopina.signedcontract.json.StringJsonPathPropertyValue;

import java.util.Arrays;

public class SignedControlHalDsl {

    public static Contract halDocument(HalDocumentClause... halClausules) {
        return new Contract(Arrays.asList(halClausules));
    }

    public static HalDocumentClause resource(JsonPathPropertyValue ... jsonPathPropertyValues) {
        return new ResourceClause(new JsonPropertyList(Arrays.asList(jsonPathPropertyValues)));
    }

    public static JsonPathPropertyValue is(String jsonPath, String value) {
        return  new StringJsonPathPropertyValue(jsonPath, value);
    }

    public static HalDocumentClause links(LinkClause... linkClausules) {
        return new LinksClause(Arrays.asList(linkClausules));
    }

     public static LinkClause link(String rel, LinkPropertyClause... linkProperties) {
        return new LinkClause(rel, Arrays.asList(linkProperties));
    }

    public static UriTemplateClause uriTemplate(UriTemplateVariableFindClause... uriTemplateVariableFindClausules) {
        return new UriTemplateClause(Arrays.asList(uriTemplateVariableFindClausules));
    }

    public static UriTemplateVariableFindClause variable(String varName, UriTemplateVariableClause... uriTemplateVariableClausules) {
        return new UriTemplateVariableFindClause(varName, Arrays.asList(uriTemplateVariableClausules));
    }

    public static UriTemplateVariableClause isSimpleExpansion() {
        return new UriTemplateVariableOperatorClause(Operator.NUL);
    }

    public static UriTemplateVariableClause isPath() {
        return new UriTemplateVariableOperatorClause(Operator.PATH);
    }

    public static UriTemplateVariableClause isQuery() {
        return new UriTemplateVariableOperatorClause(Operator.QUERY);
    }

    public static UriTemplateVariableClause isExploded() {
        return new UriTemplateVariableModifierClause(Modifier.EXPLODE);
    }

}
