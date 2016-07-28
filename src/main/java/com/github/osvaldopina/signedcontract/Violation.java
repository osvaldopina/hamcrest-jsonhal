package com.github.osvaldopina.signedcontract;

import java.util.ArrayList;
import java.util.List;

public class Violation {

    private List<Violation> subViolations;

    public Violation(List<Violation> subViolations) {
        this.subViolations = new ArrayList<Violation>(subViolations);
    }

    public List<Violation> getSubViolations() {
        return subViolations;
    }


}
