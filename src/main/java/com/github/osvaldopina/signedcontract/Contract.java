package com.github.osvaldopina.signedcontract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contract extends BranchClausule {

    private List<Clausule> clausules;


    public Contract(List<? extends Clausule> clausules) {
        super(null, clausules);
    }

    public List<Clausule> getClausules() {
        return clausules;
    }

}
