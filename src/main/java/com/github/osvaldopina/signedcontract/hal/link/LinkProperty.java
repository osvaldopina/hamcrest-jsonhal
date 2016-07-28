package com.github.osvaldopina.signedcontract.hal.link;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.LeafClausule;

import java.io.IOException;
import java.util.Map;

public class LinkProperty extends LeafClausule {


    public String getCaseInsensitiveFromMap(Map<String,Object> map, String key) {
        for(Map.Entry<String,Object> entry:map.entrySet()) {
            if (key.toUpperCase().equals(entry.getKey().toUpperCase())) {
                return entry.getValue()==null?null:entry.getValue().toString();
            }
        }
        return null;
    }


}
