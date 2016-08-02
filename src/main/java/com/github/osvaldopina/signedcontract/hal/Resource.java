package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.BranchClausule;
import com.github.osvaldopina.signedcontract.Clausule;
import com.github.osvaldopina.signedcontract.Violation;
import com.github.osvaldopina.signedcontract.json.JsonPropertyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resource extends HalClausule{

    public Resource(JsonPropertyList subClausules) {
        super(Arrays.asList(subClausules));
    }

}
