package com.github.osvaldopina.signedcontract.json;

import com.jayway.jsonpath.JsonPath;

public class StringJsonPathPropertyValue extends JsonPathPropertyValue {

    public StringJsonPathPropertyValue(String jsonPath, String value) {
        super(jsonPath, value);
    }

    @Override
    public void enforceClause(String document) {

        Object documentValue = JsonPath.parse(document).read(getJsonPath());

        JSON_TYPE jsonType = JSON_TYPE.fromJavaType(documentValue);
        if (!(documentValue instanceof String )) {
            addViolation(new StringBuilder().append("expected ").append(getJsonPath()).append(" to be string ").append(" but it was ").append(jsonType).toString());
        }
        if (!getValue().equals(documentValue)) {
            addViolation(new StringBuilder().append("expected ").append(getJsonPath()).append(" to be \"").append(documentValue).append("\" but it was ").append(jsonType).toString());
        }
    }

}
