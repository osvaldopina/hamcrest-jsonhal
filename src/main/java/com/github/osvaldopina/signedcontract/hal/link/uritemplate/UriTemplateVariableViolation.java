package com.github.osvaldopina.signedcontract.hal.link.uritemplate;

import com.github.osvaldopina.signedcontract.Violation;

import java.util.Collections;
import java.util.List;

public class UriTemplateVariableViolation extends Violation {

    private String expected;

    private String actual;

    public UriTemplateVariableViolation(String expected, String actual) {
        super(Collections.EMPTY_LIST);
        this.actual = actual;
        this.expected = expected;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("expecting link ")
                .append(expected)
                .append(" but it was ")
                .append(actual).toString();
    }



}
