package com.github.osvaldopina.signedcontract.hal.link.uritemplate;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.impl.VarSpec;
import com.github.osvaldopina.signedcontract.BranchClausule;
import com.github.osvaldopina.signedcontract.Clausule;
import com.github.osvaldopina.signedcontract.Violation;
import com.github.osvaldopina.signedcontract.ViolationsFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UriTemplateVariableClausule extends BranchClausule {

    private String varName;

    public UriTemplateVariableClausule(String varName, List<? extends Clausule> subClausules) {
        super(subClausules);
        this.varName = varName;
    }

    @Override
    protected String dismemberDocument(String document) {

        Expression expression = getExpression(varName, document);
        VarSpec varSpec = getVarSpec(varName, document);
        return new Expression(expression.getOperator(), Arrays.asList(varSpec)).toString();
    }

    @Override
    protected List<Violation> enforceClausule(String document) {

        if (getVarSpec(varName, document) == null) {
            return ViolationsFactory.getInstance().createViolations(
                    new UriTemplateVariableViolation("to find variable " + varName + " into uri template", "could not find it!")
            );
        }
        return Collections.emptyList();

    }

    private VarSpec getVarSpec(String varName, String template) {

        UriTemplate uriTemplate = UriTemplate.fromTemplate(template);

        for (Expression expression : uriTemplate.getExpressions()) {

            for (VarSpec varSpec : expression.getVarSpecs()) {
                if (varSpec.getVariableName().equals(varName)) {
                    return varSpec;
                }
            }
        }
        return null;

    }

    private Expression getExpression(String varName, String template) {

        UriTemplate uriTemplate = UriTemplate.fromTemplate(template);

        for (Expression expression : uriTemplate.getExpressions()) {

            for (VarSpec varSpec : expression.getVarSpecs()) {
                if (varSpec.getVariableName().equals(varName)) {
                    return expression;
                }
            }
        }
        return null;

    }

}
