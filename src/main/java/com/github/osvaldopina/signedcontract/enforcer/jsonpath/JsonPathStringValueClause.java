package com.github.osvaldopina.signedcontract.enforcer.jsonpath;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Error;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonPathStringValueClause extends JsonPathClauseEnforcer {

    private String expected;

    public JsonPathStringValueClause(String path, String expected) {
        super(path);
        this.expected = expected;
    }

    @Override
    protected List<Error> validate(Object actual) {

        if (actual == null) {
            return Arrays.asList(new Error(
                    new StringBuilder()
                            .append("Was expecting \"")
                            .append(expected)
                            .append("\" for json path ")
                            .append(getPath())
                            .append(" but the path was not found in json document")
                            .toString()));
        }
        else if (! (actual instanceof  String)) {
            return Arrays.asList(new Error(
                    new StringBuilder()
                            .append("Was expecting \"")
                            .append(expected)
                            .append("\" for json path ")
                            .append(getPath())
                            .append(" to be a String but it was ")
                            .append(actual.getClass().getSimpleName())
                            .toString()));

        }
        else if (! actual.equals(expected)) {
            return Arrays.asList(new Error(
                    new StringBuilder()
                            .append("Was expecting \"")
                            .append(expected)
                            .append("\" for json path ")
                            .append(getPath())
                            .append(" but it was \"")
                            .append(actual)
                            .toString()));

        }

        return Collections.emptyList();
    }

}
