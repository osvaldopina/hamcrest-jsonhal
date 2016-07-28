package com.github.osvaldopina.signedcontract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BranchClausule implements Clausule{

    private List<Clausule> subClausules;

    public BranchClausule(List<? extends Clausule> subClausules) {
        this.subClausules = new ArrayList<Clausule>(subClausules);
    }


    public List<Clausule> getSubClausules() {
        return subClausules;
    }

    @Override
    public List<Violation> enforce(String document) {

        List<Violation> violations = enforceClause(document);

        if (! violations.isEmpty()) {
            return violations;
        }

        List<Violation> subViolations = new ArrayList<Violation>();
        for(Clausule clausule: subClausules) {
            String documentPart = clausule.dismemberDocument(document);

            subViolations.addAll(clausule.enforce(documentPart));

        }
        return Collections.unmodifiableList(subViolations);
    }

    public List<Violation> enforceClause(String document) {
        return Collections.emptyList();
    }

    public String dismemberDocument(String document) {
        return document;
    }


}
