package com.github.osvaldopina.signedcontract.json;

import java.util.List;
import java.util.Map;

/**
 * Created by deinf.osvaldo on 28/07/2016.
 */
public enum JSON_TYPE {

    STRING,
    ARRAY,
    OBJECT,
    NUMBER;

    public static JSON_TYPE fromJavaType(Object object) {
        if (object == null) {
            throw new IllegalStateException("Cannot determine json type from null value!");
        }
        else {
            return fromJavaType(object.getClass());
        }
    }

    public static JSON_TYPE fromJavaType(Class<?> javaType) {
        if (javaType == null) {
            throw new IllegalStateException("Cannot determine json type from null value");
        }
        if (List.class.isAssignableFrom(javaType)) {
            return ARRAY;
        }
        else if (String.class.isAssignableFrom(javaType)) {
            return STRING;
        }
        else if (Number.class.isAssignableFrom(javaType)) {
            return NUMBER;
        }
        else if (Map.class.isAssignableFrom(javaType)) {
            return OBJECT;
        }
        else {
            throw new IllegalStateException("class " + javaType.getName() + " is not a valid json type");
        }

    }


}
