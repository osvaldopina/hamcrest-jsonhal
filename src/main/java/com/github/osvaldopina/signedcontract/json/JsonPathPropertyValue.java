package com.github.osvaldopina.signedcontract.json;

import com.github.osvaldopina.signedcontract.LeafClause;
import com.jayway.jsonpath.JsonPath;

public class JsonPathPropertyValue extends LeafClause {

    private String jsonPath;

    private Object value;

    public JsonPathPropertyValue(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public Object getValue() {
        return value;
    }

    @Override
    protected void enforceClause(String document) {

        Object documentValue = JsonPath.parse(document).read(getJsonPath());

        if (!value.equals(documentValue)) {
            addViolation(new StringBuilder()
                    .append("expected ")
                    .append(getJsonPath())
                    .append(" to be ")
                    .append(value)
                    .append(" but it was ")
                    .append(documentValue)
                    .toString());
        }
    }

}
