package com.github.osvadopina.hamcrest.jsonhal.uri;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.impl.Modifier;
import com.damnhandy.uri.template.impl.Operator;
import com.damnhandy.uri.template.impl.VarSpec;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public abstract class UriVariableMatcher extends TypeSafeMatcher<UriTemplateVariable> {


    public static UriVariableMatcher hasSimpleExpansion() {
        return new UriVariableOperationMatcher(Operator.NUL) {
        };
    }

    public static UriVariableMatcher hasFragmentExpansion() {
        return new UriVariableOperationMatcher(Operator.FRAGMENT) {
        };

    }

    public static UriVariableMatcher hasLabelExpansion() {
        return new UriVariableOperationMatcher(Operator.NAME_LABEL) {
        };
    }

    public static UriVariableMatcher hasPathExpansion() {
        return new UriVariableOperationMatcher(Operator.PATH) {
        };
    }

    public static UriVariableMatcher hasMatrixExpansion() {
        return new UriVariableOperationMatcher(Operator.MATRIX) {
        };
    }

    public static UriVariableMatcher hasQueryExpansion() {
        return new UriVariableOperationMatcher(Operator.QUERY) {
        };
    }

    public static UriVariableMatcher hasContinuationExpansion() {
        return new UriVariableOperationMatcher(Operator.CONTINUATION) {
        };
    }

    public static UriVariableMatcher hasExplodedExpansion() {
        return new UriVariableMatcher() {

            @Override
            protected boolean matchesSafely(UriTemplateVariable item) {
                return item.getVarSpec().getModifier() == Modifier.EXPLODE;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("to have exploded operator (* operator)");
            }

            @Override
            protected void describeMismatchSafely(UriTemplateVariable actual, Description mismatchDescription) {
                mismatchDescription.appendText("to not have exploded operator (* operator)");
            }

        };
    }

    public static UriVariableMatcher hasNotExplodedExpansion() {
        return new UriVariableMatcher() {

            @Override
            protected boolean matchesSafely(UriTemplateVariable item) {
                return item.getVarSpec().getModifier() != Modifier.EXPLODE;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("to not have exploded operator (* operator)");
            }

            @Override
            protected void describeMismatchSafely(UriTemplateVariable actual, Description mismatchDescription) {
                mismatchDescription.appendText("have not exploed operator (* operator)");
            }

        };
    }

    private static class UriVariableOperationMatcher extends UriVariableMatcher {

        private Operator operator;

        public UriVariableOperationMatcher(Operator operator) {
            this.operator = operator;
        }

        @Override
        protected boolean matchesSafely(UriTemplateVariable item) {
            return Operator.fromOpCode(item.getExpression().getOperator().getOperator()) == operator;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("to have ");
            switch (operator) {
                case NUL:
                    description.appendText("<SIMPLE>");
                    break;
                case NAME_LABEL:
                    description.appendText("<LABEL>");
                    break;
                default:
                    description.appendValue(operator);
            }
            description.appendText(" expansion type");
        }

        @Override
        protected void describeMismatchSafely(UriTemplateVariable actual, Description mismatchDescription) {
            mismatchDescription
                    .appendText("has ")
                    .appendValue(actual.getExpression().getOperator())
                    .appendText(" as expansion type");
        }

    }
}