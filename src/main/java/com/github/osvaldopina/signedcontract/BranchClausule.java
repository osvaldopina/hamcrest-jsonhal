package com.github.osvaldopina.signedcontract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BranchClausule extends Clausule{

    private List<Clausule> subClausules;

    public BranchClausule(List<? extends Clausule> subClausules) {
        this.subClausules = new ArrayList<Clausule>(subClausules);
    }


    public List<Clausule> getSubClausules() {
        return subClausules;
    }

    @Override
    public final List<Violation> enforce(String document) {

        List<Violation> violations = super.enforce(document);

        if (! violations.isEmpty()) {
            return violations;
        }

        List<Violation> subViolations = new ArrayList<Violation>();
        for(Clausule clausule: subClausules) {
            subViolations.addAll(clausule.enforce(document));
        }

        return Collections.unmodifiableList(subViolations);
    }

}
