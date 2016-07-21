package com.github.osvadopina.hamcrest.jsonhal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Document {

    @JsonProperty("_embedded")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String,Object> _embedded;

    private String firstProperty;

    private int secondProperty;


    public void addEmbedded(String rel, Object value) {
        if (_embedded == null) {
            _embedded = new HashMap<String, Object>();
        }
        _embedded.put(rel, value);
    }

    public String getFirstProperty() {
        return firstProperty;
    }

    public void setFirstProperty(String firstProperty) {
        this.firstProperty = firstProperty;
    }

    public int getSecondProperty() {
        return secondProperty;
    }

    public void setSecondProperty(int secondProperty) {
        this.secondProperty = secondProperty;
    }
}
