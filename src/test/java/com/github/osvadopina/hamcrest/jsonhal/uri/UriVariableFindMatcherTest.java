package com.github.osvadopina.hamcrest.jsonhal.uri;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.UriTemplate;

import com.damnhandy.uri.template.impl.VarSpec;
import org.hamcrest.Description;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

public class UriVariableFindMatcherTest {

    private UriVariableMatcher alwaysTrueVariableMatcher = new UriVariableMatcher() {

        @Override
        public void describeTo(Description description) {
            throw new IllegalStateException("must not be called!");
        }


        @Override
        protected boolean matchesSafely(UriTemplateVariable item) {
            return true;
        }

    };

    private UriVariableMatcher alwaysFalseVariableMatcher = new UriVariableMatcher() {
        @Override
        protected boolean matchesSafely(UriTemplateVariable item) {
            return false;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("error message");
        }

        @Override
        protected void describeMismatchSafely(UriTemplateVariable item, Description mismatchDescription) {
            mismatchDescription.appendText("mismatch error message");
        }
    };

    private Expression[] expression;

    @Before
    public void setUp() {
        expression  = UriTemplate.fromTemplate("http://localhost{/var1,var2*}{?var3}").getExpressions();
    }

    @Test
    public void findMatcherVariableFoundInnerMatcherTrue() {

        assertThat(expression, new UriVariableFindMatcher("var1", alwaysTrueVariableMatcher));

    }

    @Test
    public void findMatcherVariableNotFoundInnerMatcherTrue() {

        try {

            assertThat(expression, new UriVariableFindMatcher("var4", alwaysTrueVariableMatcher));

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to find a variable with name \"var4\""),
                            containsString("the uri variables are [\"var1\",\"var2\",\"var3\"]")
                    )
            );
        }

    }
    @Test
    public void findMatcherVariableFoundInnerMatcherFalse() {

        try {

            assertThat(expression, new UriVariableFindMatcher("var1", alwaysFalseVariableMatcher));

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("for uri variable \"var1\": (error message)"),
                            containsString("for uri variable \"var1\": (mismatch error message)")
                    )
            );
        }

    }

    @Test
    public void findMatcherVariableFoundTwoInnerMatcherFalse() {

        try {

            assertThat(expression, new UriVariableFindMatcher(
                    "var1",
                    alwaysFalseVariableMatcher,
                    alwaysFalseVariableMatcher)
            );

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                       containsString("for uri variable \"var1\": (error message) and (error message)"),
                       containsString("for uri variable \"var1\": (mismatch error message) and (mismatch error message)")
                    )
            );
        }

    }

    @Test
    public void findMatcherLinkNotFoundInnerMatcherFalse() {

        try {

            assertThat(expression, new UriVariableFindMatcher(
                    "var4",
                    alwaysFalseVariableMatcher)
            );

        } catch (AssertionError e) {
            assertThat(e.getMessage(),
                    allOf(
                            containsString("to find a variable with name \"var4\""),
                            containsString("the uri variables are [\"var1\",\"var2\",\"var3\"]")
                    )
            );
        }

    }


}