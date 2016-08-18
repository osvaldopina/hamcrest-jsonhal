package com.github.osvadopina.hamcrest.jsonhal.uri;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.impl.VarSpec;
import com.github.osvadopina.hamcrest.jsonhal.link.HalLink;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

public class UriVariableFindMatcher extends TypeSafeMatcher<Expression[]> {

    private final UriVariableMatcher[] matchers;
    private final String variableName;
    private boolean found = false;
    private List<NotMatched> notMatchedVariables = new ArrayList<NotMatched>();

    public UriVariableFindMatcher(String variableName, UriVariableMatcher... matchers) {
        this.variableName = variableName;
        this.matchers = matchers;
    }

    @Override
    protected boolean matchesSafely(Expression[] expressions) {
        found = false;
        notMatchedVariables = new ArrayList<NotMatched>();
        for (Expression expression : expressions) {
            for (VarSpec varSpec : expression.getVarSpecs()) {
                if (varSpec.getVariableName().equals(variableName)) {
                    found = true;
                    for (Matcher matcher : matchers) {
                        UriTemplateVariable templateVariable = new UriTemplateVariable(expression, varSpec);
                        if (!matcher.matches(templateVariable)) {
                            notMatchedVariables.add(new NotMatched(templateVariable, matcher));
                        }
                    }
                }
            }
        }
        return found && notMatchedVariables.isEmpty();
    }

    @Override
    public void describeTo(Description description) {
        if (!found) {
            description.appendText("to find a variable with name ").appendValue(variableName);
        } else {
            if (!notMatchedVariables.isEmpty()) {
                List<Matcher> descriptions = new ArrayList<Matcher>();
                description.appendText("for uri variable ").appendValue(variableName).appendText(": ");
                for (NotMatched notMatched : notMatchedVariables) {
                    descriptions.add(notMatched.getMatcher());
                }
                description.appendList("(", ") and (", ")", descriptions);
            }
        }
    }

    @Override
    protected void describeMismatchSafely(Expression[] expressions, Description mismatchDescription) {
        if (!found) {
            mismatchDescription.appendText("the uri variables are ");
            List<String> variables = new ArrayList<String>();
            for (Expression expression:expressions) {
                for(VarSpec varSpec:expression.getVarSpecs()) {
                    variables.add(varSpec.getVariableName());
                }
            }
            mismatchDescription.appendValueList("[", ",", "]", variables);
        } else {
            if (!notMatchedVariables.isEmpty()) {
                mismatchDescription.appendText("for uri variable ").appendValue(variableName).appendText(": ");
                Description matcherDescription;
                List<String> descriptions = new ArrayList<String>();
                for (NotMatched notMatched : notMatchedVariables) {
                    matcherDescription = new StringDescription();
                    notMatched.getMatcher().describeMismatch(notMatched.getTemplateVariable(), matcherDescription);
                    descriptions.add(matcherDescription.toString());
                }
                mismatchDescription.appendText(getValueList("(", ") and (", ")", descriptions));
            }
        }
    }

    public String getVariableName() {
        return variableName;
    }

    public String getValueList(String start, String separator, String end,
                               List<String> values) {

        StringBuilder sb = new StringBuilder();
        sb.append(start);
        boolean first = true;
        for (String value : values) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(value);
        }
        sb.append(end);
        return sb.toString();

    }

    private static class NotMatched {
        private UriTemplateVariable templateVariable;
        private Matcher<?> matcher;

        private NotMatched(UriTemplateVariable templateVariable, Matcher<?> matcher) {
            this.templateVariable = templateVariable;
            this.matcher = matcher;
        }

        public UriTemplateVariable  getTemplateVariable() {
            return templateVariable;
        }

        public Matcher<?> getMatcher() {
            return matcher;
        }
    }

    public static UriVariableFindMatcher hasVariable(String variableName, UriVariableMatcher...uriVariableMatchers) {
        return new UriVariableFindMatcher(variableName, uriVariableMatchers);
    }



}
