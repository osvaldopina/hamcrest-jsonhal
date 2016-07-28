package com.github.osvaldopina.signedcontract;

import java.util.Collections;
import java.util.List;

public class LeafClausule implements Clausule{
    @Override
    public String dismemberDocument(String document) {
        return document;
    }

    @Override
    public List<Violation> enforce(String document) {
        return Collections.emptyList();
    }
}
