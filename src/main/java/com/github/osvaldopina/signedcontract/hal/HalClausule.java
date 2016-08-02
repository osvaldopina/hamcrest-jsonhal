package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.BranchClausule;
import com.github.osvaldopina.signedcontract.Clausule;

import java.util.List;

public abstract class HalClausule  extends BranchClausule {


    public HalClausule(List<? extends Clausule> subClausules) {
        super(subClausules);
    }

}
