package com.github.osvadopina.hamcrest.jsonhal.link;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateComponent;
import com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableFindMatcher;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HalLinkExpressionsMatcher extends HalLinkMatcher {

    private UriVariableFindMatcher[] uriVariableFindMatchers;

    private List<UriVariableFindMatcher> notMatched;

    public HalLinkExpressionsMatcher(UriVariableFindMatcher[] uriVariableFindMatchers) {
        this.uriVariableFindMatchers = uriVariableFindMatchers;
    }


    @Override
    public void describeTo(Description description) {
        if (!notMatched.isEmpty()) {
            description.appendText("for uri : ");
            description.appendList("(", ") and (", ")", notMatched);
        }
    }

    @Override
    protected boolean matchesSafely(HalLink item) {
        notMatched = new ArrayList<UriVariableFindMatcher>();

        for (UriVariableFindMatcher uriVariableFindMatcher : uriVariableFindMatchers) {
            if (!uriVariableFindMatcher.matches(getExpressions(item.getHRef()))) {
                notMatched.add(uriVariableFindMatcher);
            }
        }
        return notMatched.isEmpty();
    }

    private Expression[] getExpressions(String uri) {
        UriTemplateComponent[] uriComponents = UriTemplate.buildFromTemplate(uri).getComponents();
        List<Expression> expressions = new ArrayList<Expression>();
        for (UriTemplateComponent uriTemplateComponent : uriComponents) {
            if (uriTemplateComponent instanceof Expression) {
                expressions.add((Expression) uriTemplateComponent);
            }
        }
        return expressions.toArray(new Expression[]{});
    }

    @Override
    protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
        if (!notMatched.isEmpty()) {
            mismatchDescription.appendText("for uri : ");
            Description matcherDescription;
            List<String> descriptions = new ArrayList<String>();
            for (UriVariableFindMatcher uriVariableFindMatcher : notMatched) {
                matcherDescription = new StringDescription();
                uriVariableFindMatcher.describeMismatch(getExpressions(actual.getHRef()), matcherDescription);
                descriptions.add(matcherDescription.toString());
            }
            mismatchDescription.appendText(getValueList("(", ") and (", ")", descriptions));
        }
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

}
