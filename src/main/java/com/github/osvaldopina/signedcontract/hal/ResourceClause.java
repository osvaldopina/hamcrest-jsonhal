package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.ClauseImpl;
import com.github.osvaldopina.signedcontract.json.JsonPropertyList;

import java.util.Arrays;
import java.util.List;

public class ResourceClause extends HalDocumentClause {

    public ResourceClause(JsonPropertyList subClausules) {
        super(Arrays.asList(subClausules));
    }

   }
