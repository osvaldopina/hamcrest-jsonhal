package com.github.osvadopina.hamcrest.jsonhal.jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonValueMatcherTest {

    @Test
    public void isStringOk() throws Exception {

        String json = "{ value : \"value\"  }";

        Object value = JsonPath.parse(json).read("$.value");

        assertTrue(JsonValueMatcher.is("value").matches(value));

    }

    @Test
    public void isBooleanNotOk() throws Exception {

        String json = "{ value : \"value\"  }";

        Object value = JsonPath.parse(json).read("$.value");

        assertFalse(JsonValueMatcher.is("value1").matches(value));

    }


}