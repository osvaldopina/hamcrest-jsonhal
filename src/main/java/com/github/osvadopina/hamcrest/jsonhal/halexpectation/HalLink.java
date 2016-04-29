package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import java.util.Map;

public class HalLink {


    private String rel;

    private Map<String, Object> link;

    public HalLink(String rel, Map<String,Object> linkProps) {
        this.rel = rel;
        this.link = linkProps;
    }

    public String getPropertyValue(HalLinkProperty property) {
        if (property == HalLinkProperty.REL) {
            return rel;
        }
        else {
            Object propertyValue = link.get(property.getPropertyName());
            return propertyValue == null?null:(""+property);
        }
    }




}
