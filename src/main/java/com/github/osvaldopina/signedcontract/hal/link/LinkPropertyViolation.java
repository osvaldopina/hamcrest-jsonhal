package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.Violation;

import java.util.Collections;

public class LinkPropertyViolation extends Violation {

    private String expected;

    private String actual;

    public LinkPropertyViolation(String expected, String actual) {
        super(Collections.<Violation>emptyList());
        this.expected = expected;
        this.actual = actual;
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
