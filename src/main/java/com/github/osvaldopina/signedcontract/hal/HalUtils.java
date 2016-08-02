package com.github.osvaldopina.signedcontract.hal;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class HalUtils {

    public static String getResource(String document) {

        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> parsedHal = null;
        try {
            parsedHal = mapper.readValue(document, Map.class);
            parsedHal.remove("_links");
            parsedHal.remove("embedded");

            return mapper.writeValueAsString(parsedHal);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getLinks(String document) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> parsedHal = null;
        try {
            parsedHal = mapper.readValue(document, Map.class);
            return mapper.writeValueAsString(parsedHal.get("_links"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEmbedded(String document) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> parsedHal = null;
        try {
            parsedHal = mapper.readValue(document, Map.class);
            return mapper.writeValueAsString(parsedHal.get("_embedded"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String,Object> parse(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return  mapper.readValue(json, Map.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getJsonPropValueAsBoolean(String value, boolean defaultValue) {
        return true;
    }
}
