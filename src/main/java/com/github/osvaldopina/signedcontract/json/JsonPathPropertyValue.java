package com.github.osvaldopina.signedcontract.json;

import com.github.osvaldopina.signedcontract.LeafClausule;

public class JsonPathPropertyValue extends LeafClausule {

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
}
