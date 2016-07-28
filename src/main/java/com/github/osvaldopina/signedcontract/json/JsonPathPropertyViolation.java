package com.github.osvaldopina.signedcontract.json;

import com.github.osvaldopina.signedcontract.Violation;

import java.util.Collections;

public class JsonPathPropertyViolation extends Violation {

    private String jsonPath;

    private String expected;

    private String actual;

    public JsonPathPropertyViolation(String jsonPath, String expected, String actual) {
        super(Collections.EMPTY_LIST);
        this.jsonPath = jsonPath;
        this.expected = expected;
        this.actual = actual;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public String getExpected() {
        return expected;
    }

    public String getActual() {
        return actual;
    }

    public String toString() {
        return new StringBuilder()
                .append("expected ")
                .append(jsonPath)
                .append(" to be ")
                .append(expected)
                .append(" but it was ")
                .append(actual).toString();
    }
}
