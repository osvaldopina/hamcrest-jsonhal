package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.BranchClause;
import com.github.osvaldopina.signedcontract.ClauseImpl;

import java.util.List;

public abstract class HalDocumentClause extends BranchClause  {


    public HalDocumentClause(List<? extends ClauseImpl> subClausules) {
        super(subClausules);
    }

}
