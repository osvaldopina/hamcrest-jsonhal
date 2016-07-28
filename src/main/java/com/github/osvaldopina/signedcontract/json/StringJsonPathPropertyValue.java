package com.github.osvaldopina.signedcontract.json;

import com.github.osvaldopina.signedcontract.Violation;
import com.jayway.jsonpath.JsonPath;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringJsonPathPropertyValue extends JsonPathPropertyValue {

    public StringJsonPathPropertyValue(String jsonPath, String value) {
        super(jsonPath, value);
    }

    @Override
    public List<Violation> enforce(String document) {

        Object documentValue = JsonPath.parse(document).read(getJsonPath());

        JSON_TYPE jsonType = JSON_TYPE.fromJavaType(documentValue);
        if (!(documentValue instanceof String )) {
            return Arrays.asList(
                    (Violation) new JsonPathPropertyViolation(getJsonPath(), "\""+ getValue() +"\"", ""+jsonType));
        }
        if (!getValue().equals(documentValue)) {
            return Arrays.asList(
                    (Violation) new JsonPathPropertyViolation(getJsonPath(), "\""+ getValue() +"\"", "\""+documentValue+"\""));
        }
        return Collections.emptyList();
    }

}
