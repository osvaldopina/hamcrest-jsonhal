package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import java.util.Map;

public class HalLink {

    private String rel;

    private Map<String, Object> link;

    public HalLink(String rel, Map<String,Object> linkProps) {
        this.rel = rel;
        this.link = linkProps;
    }

    public String getRel() {
        return rel;
    }

    public String getHRef() {
        return getCaseInsensitiveFromMap("href");
    }

    public boolean isTemplated() {
        String templated = getCaseInsensitiveFromMap("templated");
        return templated==null?false:Boolean.valueOf(getCaseInsensitiveFromMap("template"));
    }

    public String getType() {
        return getCaseInsensitiveFromMap("type");
    }

    public String getHRefLang() {
        return getCaseInsensitiveFromMap("hreflang");
    }

    private String getCaseInsensitiveFromMap(String key) {
        for(Map.Entry<String,Object> entry:link.entrySet()) {
            if (key.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue()==null?null:entry.getValue().toString();
            }
        }
        return null;
    }




}
