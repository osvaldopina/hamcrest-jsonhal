package com.github.osvaldopina.signedcontract.hal.link.uritemplate;


import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.impl.Modifier;

public class UriTemplateVariableModifierClause extends UriTemplateVariableClause {


    private Modifier expected;

    public UriTemplateVariableModifierClause(Modifier expected) {
        this.expected = expected;
    }


    @Override
    protected void enforceClause(String document) {

        UriTemplate uriTemplate = UriTemplate.fromTemplate(document);

        Modifier actual = uriTemplate.getExpressions()[0].getVarSpecs().get(0).getModifier();
        if (actual != expected) {
            addViolation(new StringBuilder()
                    .append("Expecting template variable to have modifier ")
                    .append(expected)
                    .append(" but it was ")
                    .append(actual)
                    .toString()
            );
        }
    }
}
