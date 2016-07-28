package com.github.osvaldopina.signedcontract.json;

import com.github.osvaldopina.signedcontract.BranchClausule;
import com.github.osvaldopina.signedcontract.Clausule;

import java.util.List;

public class JsonPropertyList extends BranchClausule{

    public JsonPropertyList(List<JsonPathPropertyValue> subClausules) {
        super(subClausules);
    }
}
