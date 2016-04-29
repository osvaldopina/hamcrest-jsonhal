package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

/**
 * Created by deinf.osvaldo on 26/04/2016.
 */
public enum HalLinkProperty {

    REL("rel"),
    HREF("href"),
    TEMPLATED("templated"),
    TYPE("type"),
    DEPRECATION("deprecation"),
    TITLE("title"),
    HREF_LANG("hreflang");

    private String propertyName;

    HalLinkProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
