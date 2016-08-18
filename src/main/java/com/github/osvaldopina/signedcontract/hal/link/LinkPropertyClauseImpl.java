package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.LeafClause;

import java.util.Map;

public abstract class LinkPropertyClauseImpl extends LeafClause implements LinkPropertyClause {


    public String getCaseInsensitiveFromMap(Map<String,Object> map, String key) {
        for(Map.Entry<String,Object> entry:map.entrySet()) {
            if (key.toUpperCase().equals(entry.getKey().toUpperCase())) {
                return entry.getValue()==null?null:entry.getValue().toString();
            }
        }
        return null;
    }


}
