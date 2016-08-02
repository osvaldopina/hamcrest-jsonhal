package com.github.osvaldopina.signedcontract;

import java.util.Collections;
import java.util.List;

public class Clausule {

    protected String dismemberDocument(String document) {
        return document;
    }

    protected List<Violation> enforceClausule(String document) {

        return Collections.EMPTY_LIST;
    }

    public List<Violation> enforce(String document) {

        String documentClausulePart = dismemberDocument(document);

        return  enforceClausule(documentClausulePart);
    }

}
