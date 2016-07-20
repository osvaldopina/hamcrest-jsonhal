package com.github.osvadopina.hamcrest.jsonhal.uri;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.impl.VarSpec;

public class UriTemplateVariable {

    private Expression expression;

    private VarSpec varSpec;

    public UriTemplateVariable(Expression expression, VarSpec varSpec) {
        this.expression = expression;
        this.varSpec = varSpec;
    }

    public Expression getExpression() {
        return expression;
    }

    public VarSpec getVarSpec() {
        return varSpec;
    }
}
