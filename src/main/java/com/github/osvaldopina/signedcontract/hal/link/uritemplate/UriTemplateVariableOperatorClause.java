package com.github.osvaldopina.signedcontract.hal.link.uritemplate;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.impl.Operator;

public class UriTemplateVariableOperatorClause extends UriTemplateVariableClause {


    private Operator expected;

    public UriTemplateVariableOperatorClause(Operator expected) {
        this.expected = expected;
    }


    @Override
    protected void enforceClause(String document) {

        UriTemplate uriTemplate = UriTemplate.fromTemplate(document);

        Operator actual = uriTemplate.getExpressions()[0].getOperator();
        if (actual != expected) {
            addViolation(new StringBuilder()
                    .append("Expecting uri template variable to have operator ")
                    .append(expected)
                    .append(" but it was ")
                    .append(actual)
                    .toString()
            );
        }
    }
}
