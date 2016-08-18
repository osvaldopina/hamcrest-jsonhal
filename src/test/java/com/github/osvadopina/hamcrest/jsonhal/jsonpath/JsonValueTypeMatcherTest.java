package com.github.osvadopina.hamcrest.jsonhal.jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonValueTypeMatcherTest {

    @Test
    public void isBooleanOk() throws Exception {
        String booleanValue = "{ value : true }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertTrue(JsonValueTypeMatcher.isBoolean().matches(value));

    }

    @Test
    public void isBooleanNotOk() throws Exception {
        String booleanValue = "{ value : 123 }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertFalse(JsonValueTypeMatcher.isBoolean().matches(value));

    }

    @Test
    public void isNumberOk() throws Exception {
        String booleanValue = "{ value : 125 }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertTrue(JsonValueTypeMatcher.isNumber().matches(value));

    }

    @Test
    public void isNumberNotOk() throws Exception {
        String booleanValue = "{ value : true }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertFalse(JsonValueTypeMatcher.isNumber().matches(value));

    }

    @Test
    public void isStringOk() throws Exception {
        String booleanValue = "{ value : \"value\" }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertTrue(JsonValueTypeMatcher.isString().matches(value));

    }

    @Test
    public void isStringNotOk() throws Exception {
        String booleanValue = "{ value : true }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertFalse(JsonValueTypeMatcher.isString().matches(value));

    }

    @Test
    public void isArrayOk() throws Exception {
        String booleanValue = "{ value : [1,2] }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertTrue(JsonValueTypeMatcher.isArray().matches(value));

    }

    @Test
    public void isArrayNotOk() throws Exception {
        String booleanValue = "{ value : true }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertFalse(JsonValueTypeMatcher.isArray().matches(value));

    }

    @Test
    public void isObjectOk() throws Exception {
        String booleanValue = "{ value : {} }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertTrue(JsonValueTypeMatcher.isObject().matches(value));

    }

    @Test
    public void isObjectNotOk() throws Exception {
        String booleanValue = "{ value : true }";

        Object value = JsonPath.parse(booleanValue).read("$.value");

        assertFalse(JsonValueTypeMatcher.isObject().matches(value));

    }


}