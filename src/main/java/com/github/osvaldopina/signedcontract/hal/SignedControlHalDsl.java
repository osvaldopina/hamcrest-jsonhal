package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.Contract;
import com.github.osvaldopina.signedcontract.hal.link.Link;
import com.github.osvaldopina.signedcontract.hal.link.LinkProperty;
import com.github.osvaldopina.signedcontract.hal.link.Templated;
import com.github.osvaldopina.signedcontract.json.JsonPathPropertyValue;
import com.github.osvaldopina.signedcontract.json.JsonPropertyList;
import com.github.osvaldopina.signedcontract.json.StringJsonPathPropertyValue;

import java.util.Arrays;

public class SignedControlHalDsl {

    public static Contract halDocument(HalClausule ... halClausules) {
        return new Contract(Arrays.asList(halClausules));
    }

    public static HalClausule resource(JsonPathPropertyValue ... jsonPathPropertyValues) {
        return new Resource (new JsonPropertyList(Arrays.asList(jsonPathPropertyValues)));
    }

    public static JsonPathPropertyValue is(String jsonPath, String value) {
        return  new StringJsonPathPropertyValue(jsonPath, value);
    }

    public static HalClausule links(Link... links) {
        return new Links(Arrays.asList(links));
    }

     public static Link link(String rel, LinkProperty ... linkProperties) {
        return new Link(rel, Arrays.asList(linkProperties));
    }

    public static LinkProperty isTemplated() {
        return new Templated()
    }


}
