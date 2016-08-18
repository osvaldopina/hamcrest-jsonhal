package com.github.osvaldopina.signedcontract.json;

import com.github.osvaldopina.signedcontract.BranchClause;

import java.util.List;

public class JsonPropertyList extends BranchClause {

    public JsonPropertyList(List<JsonPathPropertyValue> subClausules) {
        super(subClausules);
    }
}
