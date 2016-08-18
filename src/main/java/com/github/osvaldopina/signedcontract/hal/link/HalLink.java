package com.github.osvaldopina.signedcontract.hal.link;

import java.util.Map;

public class HalLink {

    private Map<String, Object> link;

    private String rel;

    public HalLink(Map<String,Object> linkProps) {
        assert linkProps.keySet().size() == 1 : "link must have a rel as the only property entry";

        this.rel = linkProps.keySet().toArray()[0].toString();
        this.link = (Map<String,Object>) linkProps.get(rel);
    }

    public String getRel() {
        return  rel;
    }

    public String getHRef() {
        return getCaseInsensitiveFromMap("href");
    }

    public boolean isTemplated() {
        String templated = getCaseInsensitiveFromMap("templated");
        return templated==null?false:Boolean.valueOf(getCaseInsensitiveFromMap("templated"));
    }

    public String getType() {
        return getCaseInsensitiveFromMap("type");
    }

    public String getHRefLang() {
        return getCaseInsensitiveFromMap("hreflang");
    }

    public String getCaseInsensitiveFromMap(String key) {
        for(Map.Entry<String,Object> entry:link.entrySet()) {
            if (key.toUpperCase().equals(entry.getKey().toUpperCase())) {
                return entry.getValue()==null?null:entry.getValue().toString();
            }
        }
        return null;
    }

}
