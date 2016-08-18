package com.github.osvadopina.hamcrest.jsonhal.uri;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.impl.VarSpec;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class UriVariableMatcherTest {

    private Expression expression;

    @Test
    public void isSimpleExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{?var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasSimpleExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to have <SIMPLE> expansion type"),
                            containsString("has <QUERY> as expansion type")
                    )
            );
        }

    }

    @Test
    public void isSimpleExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasSimpleExpansion());


    }

    @Test
    public void isFragmentExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{?var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasFragmentExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to have <FRAGMENT> expansion type"),
                            containsString("has <QUERY> as expansion type")
                    )
            );
        }

    }

    @Test
    public void isFragmentExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{#var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasFragmentExpansion());


    }

    @Test
    public void isLabelExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{?var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasLabelExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to have <LABEL> expansion type"),
                            containsString("has <QUERY> as expansion type")
                    )
            );
        }

    }

    @Test
    public void isLabelExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{.var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasLabelExpansion());


    }

    @Test
    public void isPathExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{?var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasPathExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to have <PATH> expansion type"),
                            containsString("has <QUERY> as expansion type")
                    )
            );
        }

    }

    @Test
    public void isPathExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{/var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasPathExpansion());


    }

    @Test
    public void isMatrixExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{?var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasMatrixExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to have <MATRIX> expansion type"),
                            containsString("has <QUERY> as expansion type")
                    )
            );
        }

    }

    @Test
    public void isMatrixExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{;var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasMatrixExpansion());


    }

    @Test
    public void isQueryExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{/var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasQueryExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to have <QUERY> expansion type"),
                            containsString("has <PATH> as expansion type")
                    )
            );
        }

    }

    @Test
    public void isQueryExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{?var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasQueryExpansion());


    }

    @Test
    public void isContinuationExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{/var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasContinuationExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to have <CONTINUATION> expansion type"),
                            containsString("has <PATH> as expansion type")
                    )
            );
        }

    }

    @Test
    public void isContinuationExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{&var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasContinuationExpansion());


    }

    @Test
    public void isExplodedExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{/var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasExplodedExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),containsString("to have exploded operator (* operator)"));
        }

    }

    @Test
    public void isExplodedExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{&var1*}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasExplodedExpansion());


    }

    @Test
    public void isNotExplodedExpansionError() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{/var1*}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        try {

            assertThat(variable, UriVariableMatcher.hasExplodedExpansion());

        } catch (AssertionError e) {
            assertThat(e.getMessage(),containsString("to not have exploded operator (* operator)"));
        }

    }

    @Test
    public void isNotExplodedExpansion() throws Exception {

        expression = (Expression) UriTemplate.buildFromTemplate("{&var1}").getComponents()[0];
        VarSpec varSpec = expression.getVarSpecs().get(0);

        UriTemplateVariable variable = new UriTemplateVariable(expression, varSpec);

        assertThat(variable, UriVariableMatcher.hasNotExplodedExpansion());


    }

}