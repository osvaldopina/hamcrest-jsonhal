package com.github.osvaldopina.signedcontract.json;

import com.github.osvaldopina.signedcontract.LeafClause;

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
        todo
    }
}
